/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.banik.registrations.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author majky
 */
@Entity
@Table(name = "Note", catalog = "mydb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Note.findAll", query = "SELECT n FROM Note n"),
    @NamedQuery(name = "Note.findById", query = "SELECT n FROM Note n WHERE n.notePK.id = :id"),
    @NamedQuery(name = "Note.findByCreatedDate", query = "SELECT n FROM Note n WHERE n.createdDate = :createdDate"),
    @NamedQuery(name = "Note.findByText", query = "SELECT n FROM Note n WHERE n.text = :text"),
    @NamedQuery(name = "Note.findByRegistrationIco", query = "SELECT n FROM Note n WHERE n.notePK.registrationIco = :registrationIco"),
    @NamedQuery(name = "Note.findByRegistrationRegDate", query = "SELECT n FROM Note n WHERE n.notePK.registrationRegDate = :registrationRegDate")})
public class Note implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected NotePK notePK;
    @Size(max = 45)
    @Column(name = "created_date")
    private String createdDate;
    @Size(max = 45)
    @Column(name = "text")
    private String text;
    @JoinColumns({
        @JoinColumn(name = "registration_ico", referencedColumnName = "ico", insertable = false, updatable = false),
        @JoinColumn(name = "registration_reg_date", referencedColumnName = "reg_date", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Registration registration;

    public Note() {
    }

    public Note(NotePK notePK) {
        this.notePK = notePK;
    }

    public Note(int id, String registrationIco, Date registrationRegDate) {
        this.notePK = new NotePK(id, registrationIco, registrationRegDate);
    }

    public NotePK getNotePK() {
        return notePK;
    }

    public void setNotePK(NotePK notePK) {
        this.notePK = notePK;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (notePK != null ? notePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Note)) {
            return false;
        }
        Note other = (Note) object;
        if ((this.notePK == null && other.notePK != null) || (this.notePK != null && !this.notePK.equals(other.notePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sk.banik.registrations.entities.Note[ notePK=" + notePK + " ]";
    }
    
}
