/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.quadrum.contratos.controller.service.user;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import mx.com.quadrum.entity.Contacto;
import mx.com.quadrum.entity.Contrato;
import mx.com.quadrum.entity.Permiso;
import mx.com.quadrum.service.ContactoService;
import mx.com.quadrum.service.ContratoService;
import mx.com.quadrum.service.EstatusService;
import mx.com.quadrum.service.TipoContratoService;
import static mx.com.quadrum.service.util.Llave.CLIENTE;
import static mx.com.quadrum.service.util.Llave.PERMISOS;
import static mx.com.quadrum.service.util.Llave.USUARIO;
import mx.com.quadrum.service.util.firma.Firma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author vcisneros
 */
@Controller
public class ClientesController {

    @Autowired
    ContactoService contactoService;

    @Autowired
    ContratoService contratoService;

    @Autowired
    TipoContratoService tipoContratoService;

    @Autowired
    EstatusService estatusService;

    @RequestMapping(value = "inicioCliente")
    public String inicioCliente(String rfc, String password, Model model, HttpSession session) {
        if (contactoService.estaRegistrado(rfc, password)) {
            Contacto contacto = contactoService.buscarPorCorreo(rfc);
            session.setAttribute(PERMISOS, new ArrayList<Permiso>());
            session.setAttribute(USUARIO, null);
            session.setAttribute(CLIENTE, contacto);

            if (contacto.getPrimeraSesion()) {
                return "cliente/cambiarPass";
            }
            model.addAttribute("url", "welcomeCliente");
            return "templates/redireccionador";
        }
        return "cliente/index";
    }

    @RequestMapping(value = "welcomeCliente")
    public String welcomeCliente(String rfc, String password, Model model, HttpSession session) {
        Contacto contacto = (Contacto) session.getAttribute(CLIENTE);
        if (contacto == null) {
            return "cliente/index";
        }
        List<Contrato> contratos = contratoService.buscarPorContacto(contacto.getId());
        model.addAttribute("contacto", contacto);
        model.addAttribute("contratos", contratos);
        model.addAttribute("tipoContrato", tipoContratoService.buscarTodos());
        model.addAttribute("estado", estatusService.buscarTodos());
        return "cliente/contratos";
    }

    @RequestMapping(value = "inicioCambioPasswordCliente", method = RequestMethod.POST)
    public ModelAndView cambiarPassword(String password, HttpSession session) {
        Contacto contacto = (Contacto) session.getAttribute(CLIENTE);
        if (contacto == null) {
            session.invalidate();
            return new ModelAndView("templates/error", "error", "No se pudo cambiar el password");
        }
        List<Contrato> contratos = contratoService.buscarPorContacto(contacto.getId());
        if (contactoService.cambiarPassword(contacto, password)) {
            return new ModelAndView("cliente/contratos", "contratos", contratos);
        }
        session.invalidate();
        return new ModelAndView("templates/error", "error", "No se pudo cambiar el password");
    }

    @RequestMapping(value = "firmarPF", method = RequestMethod.POST)
    public String firmar(HttpSession session, Model model,
            @RequestParam("ife") MultipartFile ife, @RequestParam("firma") MultipartFile firma,
            @RequestParam("cer") MultipartFile cer, @RequestParam("idContrato") Integer idContrato,
            @RequestParam("key") MultipartFile key, @RequestParam("password") String password
    ) {
        Firma f;
        f = new Firma();
        //f.setPoderNotarial(poderNotarial);
        f.setIfe(ife);
        f.setFirma(firma);
        f.setCer(cer);
        f.setKey(key);
        f.setIdContrato(idContrato);
        f.setPassword(password);
        f.setContrato(contratoService.buscarPorId(idContrato));
        f.setContacto(contactoService.buscarPorId(((Contacto) session.getAttribute(CLIENTE)).getId()));
        f.setRfc(f.getContacto().getRfc());
        contratoService.firmar(f);
        for (String e : f.getErrores()) {
            System.out.println(e);
        }
        model.addAttribute("url", "welcomeCliente");
        return "templates/redireccionador";

    }
    
    @ResponseBody
    @RequestMapping(value = "dameRfc", method = RequestMethod.POST)
    public String dameRfc(HttpSession session){
        Contacto contacto = (Contacto)session.getAttribute(CLIENTE);
        if(contacto != null){
            return contacto.getRfc();
        }
        return null;
    }
    
    
    @RequestMapping(value = "firmarPM", method = RequestMethod.POST)
    public String firmarPM(HttpSession session, Model model, @RequestParam("poderNotarial") MultipartFile poderNotarial,
            @RequestParam("ife") MultipartFile ife, @RequestParam("firma") MultipartFile firma,
            @RequestParam("cer") MultipartFile cer, @RequestParam("idContrato") Integer idContrato,
            @RequestParam("key") MultipartFile key, @RequestParam("password") String password
    ) {
        Firma f;
        f = new Firma();
        f.setPoderNotarial(poderNotarial);
        f.setIfe(ife);
        f.setFirma(firma);
        f.setCer(cer);
        f.setKey(key);
        f.setIdContrato(idContrato);
        f.setPassword(password);
        f.setContrato(contratoService.buscarPorId(idContrato));
        f.setContacto(contactoService.buscarPorId(((Contacto) session.getAttribute(CLIENTE)).getId()));
        f.setRfc(f.getContacto().getRfc());
        contratoService.firmar(f);
        model.addAttribute("url", "welcomeCliente");
        return "templates/redireccionador";

    }
    
}
