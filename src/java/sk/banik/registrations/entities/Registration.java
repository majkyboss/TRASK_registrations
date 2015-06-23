/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.banik.registrations.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author majky
 */
@Entity
@Table(name = "Registration", catalog = "mydb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Registration.findAll", query = "SELECT r FROM Registration r"),
    @NamedQuery(name = "Registration.findByIco", query = "SELECT r FROM Registration r WHERE r.registrationPK.ico = :ico"),
    @NamedQuery(name = "Registration.findByCompanyName", query = "SELECT r FROM Registration r WHERE r.companyName = :companyName"),
    @NamedQuery(name = "Registration.findByRegDate", query = "SELECT r FROM Registration r WHERE r.registrationPK.regDate = :regDate"),
    @NamedQuery(name = "Registration.findByAgent", query = "SELECT r FROM Registration r WHERE r.unit.user.id = :userId"),
    @NamedQuery(name = "Registration.findByManager", query = "SELECT r FROM Registration r JOIN r.unit u JOIN u.branch b WHERE b.managerId.id = :userId")
    })
public class Registration implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public static String QUERY_FIND_BY_AGENT = "Registration.findByAgent";
    public static String QUERY_FIND_BY_MANAGER = "Registration.findByManager";
    
    @EmbeddedId
    protected RegistrationPK registrationPK;
    @Size(max = 45)
    @Column(name = "company_name")
    private String companyName;
    @JoinColumns({
        @JoinColumn(name = "unit_user_id", referencedColumnName = "user_id"),
        @JoinColumn(name = "unit_branch_id", referencedColumnName = "branch_id")})
    @ManyToOne(optional = false)
    private Unit unit;
    @JoinColumn(name = "regState_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private RegState regStateid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "registration")
    private Collection<Note> noteCollection;

    public Registration() {
    }

    public Registration(RegistrationPK registrationPK) {
        this.registrationPK = registrationPK;
    }

    public Registration(String ico, Date regDate) {
        this.registrationPK = new RegistrationPK(ico, regDate);
    }

    public RegistrationPK getRegistrationPK() {
        return registrationPK;
    }

    public void setRegistrationPK(RegistrationPK registrationPK) {
        this.registrationPK = registrationPK;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public RegState getRegStateid() {
        return regStateid;
    }

    public void setRegStateid(RegState regStateid) {
        this.regStateid = regStateid;
    }

    @XmlTransient
    public Collection<Note> getNoteCollection() {
        return noteCollection;
    }

    public void setNoteCollection(Collection<Note> noteCollection) {
        this.noteCollection = noteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (registrationPK != null ? registrationPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Registration)) {
            return false;
        }
        Registration other = (Registration) object;
        if ((this.registrationPK == null && other.registrationPK != null) || (this.registrationPK != null && !this.registrationPK.equals(other.registrationPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sk.banik.registrations.entities.Registration[ registrationPK=" + registrationPK + " ]";
    }
    
}
