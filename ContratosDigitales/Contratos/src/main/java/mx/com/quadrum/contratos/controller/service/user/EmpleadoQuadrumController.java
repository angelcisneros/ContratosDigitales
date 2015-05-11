/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.quadrum.contratos.controller.service.user;

import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import mx.com.quadrum.entity.Contrato;
import mx.com.quadrum.entity.Permiso;
import mx.com.quadrum.entity.Usuario;
import mx.com.quadrum.service.ContactoService;
import mx.com.quadrum.service.ContratoService;
import mx.com.quadrum.service.EstatusService;
import mx.com.quadrum.service.TipoContratoService;
import static mx.com.quadrum.service.util.Llave.PERMISOS;
import static mx.com.quadrum.service.util.MensajesCrud.ERROR_DATOS;
import static mx.com.quadrum.service.util.MensajesCrud.SESION_CADUCA;
import mx.com.quadrum.service.util.firma.Firma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author vcisneros
 */
@Controller
public class EmpleadoQuadrumController {

    @Autowired
    ContactoService contactoService;

    @Autowired
    ContratoService contratoService;

    @Autowired
    TipoContratoService tipoContratoService;

    @Autowired
    EstatusService estatusService;

    @RequestMapping(value = "nuevoContrato", method = RequestMethod.GET)
    public String nuevoContrato(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        List<Permiso> permisos = (List<Permiso>) session.getAttribute(PERMISOS);

        if (usuario == null && permisos == null) {
            return "templates/index";
        }
        if (usuario.getEsAdmin()) {
            model.addAttribute("esAdmin", "esAdmin");
        }
        model.addAttribute("permisos", permisos);
        model.addAttribute("ocultarTxt", "ocultar");
        model.addAttribute("ocultarPdf", "ocultar");
        model.addAttribute("contactos", contactoService.buscarTodos());
        model.addAttribute("estados", estatusService.buscarTodos());
        model.addAttribute("tipoContratos", tipoContratoService.buscarTodos());
        model.addAttribute("contrato", new Contrato());
        return "usuario/nuevoContrato";
    }

    @RequestMapping(value = "contrato", method = RequestMethod.GET)
    public String contrato(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        List<Permiso> permisos = (List<Permiso>) session.getAttribute(PERMISOS);

        if (usuario == null && permisos == null) {
            return "templates/index";
        }
        model.addAttribute("permisos", permisos);
        model.addAttribute("contratos", contratoService.buscarPorUsuario(usuario.getMail()));
        model.addAttribute("tipoContratos", tipoContratoService.buscarTodos());
        model.addAttribute("estado", estatusService.buscarTodos());

        return "usuario/misContratos";
    }

    @ResponseBody
    @RequestMapping(value = "addContrato", method = RequestMethod.POST)
    public String addContrato(@Valid @ModelAttribute("contrato") Contrato contrato, BindingResult result, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            return SESION_CADUCA;
        }
        if (result.hasErrors()) {
            for (ObjectError e : result.getAllErrors()) {
                System.out.println(e);
            }
            return ERROR_DATOS;
        }
        Firma firma = new Firma();
        firma.setContrato(contrato);
        firma.setContacto(contactoService.buscarPorId(contrato.getContacto().getId()));
        return contratoService.agregar(contrato, usuario, firma);
    }

    @RequestMapping(value = "cuentasCliente", method = RequestMethod.GET)
    public String activarCuentaUsuario(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        List<Permiso> permisos = (List<Permiso>) session.getAttribute(PERMISOS);
        model.addAttribute("permisos", permisos);
        model.addAttribute("contactos", contactoService.buscarTodos());
        return "usuario/activarCuentaClientes";
    }

    @ResponseBody
    @RequestMapping(value = "cuentasCliente", method = RequestMethod.POST)
    public String activarCuentaUsuarioPost(@RequestBody String id, HttpSession session, Model model) {
        return contactoService.cambiarEstadoCuenta(Integer.parseInt(id.replace("=", "")));
    }

    @ResponseBody
    @RequestMapping(value = "visibleCliente", method = RequestMethod.POST)
    public String visibleCliente(@RequestBody String id, HttpSession session, Model model) {
        return contratoService.mostrarOcultarCliente(Integer.parseInt(id.replace("=", "")));
    }

    @ResponseBody
    @RequestMapping(value = "aprobarContrato", method = RequestMethod.POST)
    public String aprobarContrato(@RequestBody String id, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return SESION_CADUCA;
        }
        return contratoService.aprobarContrato(Integer.parseInt(id.replace("=", "")));
    }

    @ResponseBody
    @RequestMapping(value = "rechazarContrato", method = RequestMethod.POST)
    public String rechazarContrato(@RequestBody String id, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return SESION_CADUCA;
        }
        return contratoService.rechazarContrato(Integer.parseInt(id.replace("=", "")));
    }
}
