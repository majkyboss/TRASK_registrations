/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.banik.registrations.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author majky
 */
@Entity
@Table(name = "Unit", catalog = "mydb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Unit.findAll", query = "SELECT u FROM Unit u"),
    @NamedQuery(name = "Unit.findByUserId", query = "SELECT u FROM Unit u WHERE u.unitPK.userId = :userId"),
    @NamedQuery(name = "Unit.findByBranchId", query = "SELECT u FROM Unit u WHERE u.unitPK.branchId = :branchId")})
public class Unit implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String NAMED_QUERY_BY_USER_ID = "Unit.findByUserId";
    @EmbeddedId
    protected UnitPK unitPK;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unit")
    private Collection<Registration> registrationCollection;
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;
    @JoinColumn(name = "branch_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Branch branch;

    public Unit() {
    }

    public Unit(UnitPK unitPK) {
        this.unitPK = unitPK;
    }

    public Unit(int userId, int branchId) {
        this.unitPK = new UnitPK(userId, branchId);
    }

    public UnitPK getUnitPK() {
        return unitPK;
    }

    public void setUnitPK(UnitPK unitPK) {
        this.unitPK = unitPK;
    }

    @XmlTransient
    public Collection<Registration> getRegistrationCollection() {
        return registrationCollection;
    }

    public void setRegistrationCollection(Collection<Registration> registrationCollection) {
        this.registrationCollection = registrationCollection;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (unitPK != null ? unitPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Unit)) {
            return false;
        }
        Unit other = (Unit) object;
        if ((this.unitPK == null && other.unitPK != null) || (this.unitPK != null && !this.unitPK.equals(other.unitPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sk.banik.registrations.entities.Unit[ unitPK=" + unitPK + " ]";
    }
    
}
