package mx.com.quadrum.entity;
// Generated 11/03/2015 06:26:47 PM by Hibernate Tools 3.6.0


import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * PermisoUsuario generated by hbm2java
 */
@Entity
@Table(name="permiso_usuario"
    ,catalog="contradosdigitales"
)
public class PermisoUsuario  implements java.io.Serializable {


     private PermisoUsuarioId id;
     private Permiso permiso;
     private Usuario usuario;
     private String permisoUsuariocol;

    public PermisoUsuario() {
    }

	
    public PermisoUsuario(PermisoUsuarioId id, Permiso permiso, Usuario usuario) {
        this.id = id;
        this.permiso = permiso;
        this.usuario = usuario;
    }
    public PermisoUsuario(PermisoUsuarioId id, Permiso permiso, Usuario usuario, String permisoUsuariocol) {
       this.id = id;
       this.permiso = permiso;
       this.usuario = usuario;
       this.permisoUsuariocol = permisoUsuariocol;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="permiso", column=@Column(name="permiso", nullable=false) ), 
        @AttributeOverride(name="usuario", column=@Column(name="usuario", nullable=false) ) } )
    public PermisoUsuarioId getId() {
        return this.id;
    }
    
    public void setId(PermisoUsuarioId id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="permiso", nullable=false, insertable=false, updatable=false)
    public Permiso getPermiso() {
        return this.permiso;
    }
    
    public void setPermiso(Permiso permiso) {
        this.permiso = permiso;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="usuario", nullable=false, insertable=false, updatable=false)
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    
    @Column(name="permiso_usuariocol", length=45)
    public String getPermisoUsuariocol() {
        return this.permisoUsuariocol;
    }
    
    public void setPermisoUsuariocol(String permisoUsuariocol) {
        this.permisoUsuariocol = permisoUsuariocol;
    }




}


