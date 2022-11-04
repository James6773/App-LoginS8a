package com.example.login;

import com.orm.SugarRecord;

public class Usuario extends SugarRecord<Usuario> {

    private String email;
    private String usuario;
    private String clave;
    private String confClave;

    public Usuario() {

    }

    public Usuario(String email, String usuario, String clave, String confClave) {
        this.email = email;
        this.usuario = usuario;
        this.clave = clave;
        this.confClave = confClave;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {

        return clave;
    }

    public void setClave(String clave) {

        this.clave = clave;
    }

    public String getConfClave() {

        return confClave;
    }

    public void setConfClave(String confClave) {

        this.confClave = confClave;
    }
}
