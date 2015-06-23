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
public class RegistrationPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "ico")
    private String ico;
    @Basic(optional = false)
    @NotNull
    @Column(name = "reg_date")
    @Temporal(TemporalType.DATE)
    private Date regDate;

    public RegistrationPK() {
    }

    public RegistrationPK(String ico, Date regDate) {
        this.ico = ico;
        this.regDate = regDate;
    }

    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ico != null ? ico.hashCode() : 0);
        hash += (regDate != null ? regDate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegistrationPK)) {
            return false;
        }
        RegistrationPK other = (RegistrationPK) object;
        if ((this.ico == null && other.ico != null) || (this.ico != null && !this.ico.equals(other.ico))) {
            return false;
        }
        if ((this.regDate == null && other.regDate != null) || (this.regDate != null && !this.regDate.equals(other.regDate))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sk.banik.registrations.entities.RegistrationPK[ ico=" + ico + ", regDate=" + regDate + " ]";
    }
    
}
