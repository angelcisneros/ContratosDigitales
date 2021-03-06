/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.quadrum.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import mx.com.quadrum.entity.Contrato;
import mx.com.quadrum.entity.Estatus;
import mx.com.quadrum.entity.Usuario;
import mx.com.quadrum.repository.ContratoRepository;
import mx.com.quadrum.service.*;
import mx.com.quadrum.service.util.EnviarCorreo;
import static mx.com.quadrum.service.util.MensajesCrud.ADD_CORRECT;
import static mx.com.quadrum.service.util.MensajesCrud.DELETE_CORRECT;
import static mx.com.quadrum.service.util.MensajesCrud.ERROR_HIBERNATE;
import static mx.com.quadrum.service.util.MensajesCrud.UPDATE_CORRECT;
import static mx.com.quadrum.service.util.Rutas.ASUNTO_DISPONIBLE_FIRMAR;
import static mx.com.quadrum.service.util.Rutas.CADENA_ORIGINAL;
import static mx.com.quadrum.service.util.Rutas.acomodaTexto;
import static mx.com.quadrum.service.util.Rutas.clienteFirmo;
import static mx.com.quadrum.service.util.Rutas.regresarLeyenda;
import static mx.com.quadrum.service.util.Validaciones.convierteStringToFecha;
import mx.com.quadrum.service.util.firma.AtributosCertificado;
import mx.com.quadrum.service.util.firma.CadenaOriginal;
import mx.com.quadrum.service.util.firma.Firma;
import mx.com.quadrum.service.util.firma.Sello;
import mx.com.quadrum.service.util.firma.jasper.RepresentacionImpresa;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author vcisneros
 */
@Service
public class ContratoServiceImpl implements ContratoService, BusquedasContratos {

    @Autowired
    ContratoRepository contratoRepository;
    private static final String CONTRATO = "una contrato.";
    private String ASUNTO_FIRMADO;

    @Override
    public String agregar(Contrato contrato) {
        if (contratoRepository.agregar(contrato)) {

            return ADD_CORRECT + CONTRATO;
        }
        return ERROR_HIBERNATE;
    }

    @Override
    public String editar(Contrato contrato) {
        if (contratoRepository.editar(contrato)) {
            return UPDATE_CORRECT + CONTRATO;
        }
        return ERROR_HIBERNATE;
    }

    @Override
    public String eliminar(Contrato contrato) {
        if (contratoRepository.editar(contrato)) {
            return DELETE_CORRECT + CONTRATO;
        }
        return ERROR_HIBERNATE;
    }

    @Override
    public Boolean esValid(Contrato contrato) {
        return true;
    }

    @Override
    public String agregar(Contrato contrato, Usuario usuario, Firma firma) {

        contrato.setUsuario(usuario);
        contrato.setVisibleCliente(Boolean.FALSE);
        contrato.setTieneArchivos(Boolean.FALSE);
        contrato.setEditable(Boolean.TRUE);
        contrato.setEstatus(new Estatus(45));
        contratoRepository.agregar(contrato);
        contrato = contratoRepository.buscarPorId(contrato.getId());
        String clave = contrato.getTipoContrato().getNombre().substring(0, 2).toUpperCase();
        if (contrato.getContacto().getEmpresa() == null) {
            clave += "-0F-";
        } else {
            clave += "-0M-";
        }
        contrato.setNombre(clave += "7" + contrato.getId());
        contratoRepository.editar(contrato);
        RepresentacionImpresa ri = new RepresentacionImpresa(firma, usuario.getMail());
        ri.ejecutaJasper();
        return ADD_CORRECT + CONTRATO;
    }

    @Override
    public Contrato buscarPorId(Integer id) {
        return contratoRepository.buscarPorId(id);
    }

    @Override
    public List<Contrato> buscarTodos() {
        return contratoRepository.buscarTodos();
    }

    @Override
    public List<Contrato> buscarPorUsuario(String mail) {
        return contratoRepository.buscarPorUsuario(mail);
    }

    @Override
    public List<Contrato> buscarPorContacto(Integer id) {
        return contratoRepository.buscarParaContacto(id);
    }

    @Override
    public String mostrarOcultarCliente(Integer id) {
        Contrato contrato = contratoRepository.buscarPorId(id);
        contrato.setVisibleCliente(!contrato.getVisibleCliente());
        if (contratoRepository.editar(contrato)) {
            if (contrato.getVisibleCliente()) {
                EnviarCorreo enviar = new EnviarCorreo();
                try {
                    enviar.enviaCredenciales(contrato.getContacto().getMail(), ASUNTO_DISPONIBLE_FIRMAR, acomodaTexto(contrato));
                    return "Correcto...#Ahora el cliente a recibido una notificación de que puede ver el contrato";
                } catch (MessagingException ex) {
                    Logger.getLogger(ContratoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                return "Correcto...#Se ha ocultado el contrato al cliente.";
            }

        }
        return "Error...#Se produjo un error al querer mostrar el contrato al cliente. Intente más tarde";
    }

    @Override
    public Boolean firmar(Firma f) {
        Contrato contrato = f.getContrato();
        if (f.validaCampos()) {
            try {
                ByteArrayOutputStream byteArrayOutputStreamCer = new ByteArrayOutputStream();
                ByteArrayOutputStream byteArrayOutputStreamKey = new ByteArrayOutputStream();

                IOUtils.copy(f.getCer().getInputStream(), byteArrayOutputStreamCer);
                IOUtils.copy(f.getKey().getInputStream(), byteArrayOutputStreamKey);
                if (!f.esValidoCerKeyAndPassword()) {
                    return false;
                }
                if (f.firmar()) {
                    if (f.guardarArchivos()) {
                        AtributosCertificado cer = new AtributosCertificado(new ByteArrayInputStream(byteArrayOutputStreamCer.toByteArray()));
                        InputStream leyenda = IOUtils.toInputStream(regresarLeyenda(contrato.getNombre()));
                        CadenaOriginal objetoCadenaOriginal = new CadenaOriginal();

                        byte[] cadenaOriginal = objetoCadenaOriginal.generaCadenaDos(leyenda, CADENA_ORIGINAL);

                        Sello miSello = new Sello(f.getPassword(), f.getKey().getBytes(), cadenaOriginal);
                        cer.obtenDatosCertificado();

                        Calendar c = Calendar.getInstance();
                        contrato.setSello(miSello.GeneraSelloDigital());
                        contrato.setEstatus(new Estatus(48));
                        contrato.setTieneArchivos(Boolean.TRUE);
                        contrato.setFechaFirma(convierteStringToFecha(c.get(Calendar.DATE) + "/" + (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.YEAR)));

                        contratoRepository.editar(contrato);
                        EnviarCorreo enviar = new EnviarCorreo();
                        try {
                            enviar.enviaCredenciales(contrato.getUsuario().getMail(), clienteFirmo(contrato), ASUNTO_FIRMADO);
                        } catch (MessagingException ex) {
                            Logger.getLogger(ContratoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        return true;
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(ContratoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;

    }

    @Override
    public List<Contrato> buscarPorTipoDeContrato(Integer idTipoContrato) {
        return contratoRepository.buscarPorTipoDeContrato(idTipoContrato);
    }

    @Override
    public List<Contrato> buscarPorEstado(Integer idEstado) {
        return contratoRepository.buscarPorEstado(idEstado);
    }

    @Override
    public List<Contrato> buscarPorEmpresa(String nombre) {
        return contratoRepository.buscarPorEmpresa(nombre);
    }

    @Override
    public List<Contrato> buscarPorContacto(String nombre) {
        return contratoRepository.buscarPorContacto(nombre);
    }

    @Override
    public List<Contrato> buscarPorContacto(String nombre, String paterno) {
        return contratoRepository.buscarPorContacto(nombre, paterno);
    }

    @Override
    public List<Contrato> buscarPorContaco(String nombre, String paterno, String materno) {
        return contratoRepository.buscarPorContaco(nombre, paterno, materno);
    }

    @Override
    public List<Contrato> buscarPorEmpleado(Integer idEmpleado) {
        return contratoRepository.buscarPorEmpleado(idEmpleado);
    }

    @Override
    public List<Contrato> buscarPorTipoDeContratoEmpleado(Integer idTipoContrato, Integer idEmpleado) {
        return contratoRepository.buscarPorTipoDeContratoEmpleado(idTipoContrato, idEmpleado);
    }

    @Override
    public List<Contrato> buscarParaContactoEmpleado(Integer id, Integer idEmpleado) {
        return contratoRepository.buscarParaContactoEmpleado(id, idEmpleado);
    }

    @Override
    public List<Contrato> buscarPorEstadoEmpleado(Integer idEstado, Integer idEmpleado) {
        return contratoRepository.buscarPorEstadoEmpleado(idEstado, idEmpleado);
    }

    @Override
    public List<Contrato> buscarPorEmpresaEmpleado(String nombre, Integer idEmpleado) {
        return contratoRepository.buscarPorEmpresaEmpleado(nombre, idEmpleado);
    }

    @Override
    public List<Contrato> buscarPorContactoEmpleado(String nombre, Integer idEmpleado) {
        return contratoRepository.buscarPorContactoEmpleado(nombre, idEmpleado);
    }

    @Override
    public List<Contrato> buscarPorContactoEmpleado(String nombre, String paterno, Integer idEmpleado) {
        return contratoRepository.buscarPorContactoEmpleado(nombre, paterno, idEmpleado);
    }

    @Override
    public List<Contrato> buscarPorContacoEmpleado(String nombre, String paterno, String materno, Integer idEmpleado) {
        return contratoRepository.buscarPorContacoEmpleado(nombre, paterno, materno, idEmpleado);
    }

    @Override
    public List<Contrato> buscarPorTipoDeContratoCliente(Integer idTipoContrato, Integer idCliente) {
        return contratoRepository.buscarPorTipoDeContratoCliente(idTipoContrato, idCliente);
    }

    @Override
    public List<Contrato> buscarPorEstadoCliente(Integer idEstado, Integer idCliente) {
        return contratoRepository.buscarPorEstadoCliente(idEstado, idCliente);
    }

}
