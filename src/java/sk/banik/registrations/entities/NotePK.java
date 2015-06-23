/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.banik.registrations.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author majky
 */
@Embeddable
public class NotePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "registration_ico")
    private String registrationIco;
    @Basic(optional = false)
    @NotNull
    @Column(name = "registration_reg_date")
    @Temporal(TemporalType.DATE)
    private Date registrationRegDate;

    public NotePK() {
    }

    public NotePK(int id, String registrationIco, Date registrationRegDate) {
        this.id = id;
        this.registrationIco = registrationIco;
        this.registrationRegDate = registrationRegDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegistrationIco() {
        return registrationIco;
    }

    public void setRegistrationIco(String registrationIco) {
        this.registrationIco = registrationIco;
    }

    public Date getRegistrationRegDate() {
        return registrationRegDate;
    }

    public void setRegistrationRegDate(Date registrationRegDate) {
        this.registrationRegDate = registrationRegDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (registrationIco != null ? registrationIco.hashCode() : 0);
        hash += (registrationRegDate != null ? registrationRegDate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NotePK)) {
            return false;
        }
        NotePK other = (NotePK) object;
        if (this.id != other.id) {
            return false;
        }
        if ((this.registrationIco == null && other.registrationIco != null) || (this.registrationIco != null && !this.registrationIco.equals(other.registrationIco))) {
            return false;
        }
        if ((this.registrationRegDate == null && other.registrationRegDate != null) || (this.registrationRegDate != null && !this.registrationRegDate.equals(other.registrationRegDate))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sk.banik.registrations.entities.NotePK[ id=" + id + ", registrationIco=" + registrationIco + ", registrationRegDate=" + registrationRegDate + " ]";
    }
    
}
