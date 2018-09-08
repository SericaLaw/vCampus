package team.yummy.vCampus.models.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "USysApplicationLog", schema = "\".\"", catalog = "\".\"")
public class USysApplicationLogEntity {
    private Integer id;
    private String sourceObject;
    private String dataMacroInstanceId;
    private Integer errorNumber;
    private String category;
    private String objectType;
    private String description;
    private String context;
    private Timestamp created;

    @Id
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "SourceObject")
    public String getSourceObject() {
        return sourceObject;
    }

    public void setSourceObject(String sourceObject) {
        this.sourceObject = sourceObject;
    }

    @Basic
    @Column(name = "Data Macro Instance ID")
    public String getDataMacroInstanceId() {
        return dataMacroInstanceId;
    }

    public void setDataMacroInstanceId(String dataMacroInstanceId) {
        this.dataMacroInstanceId = dataMacroInstanceId;
    }

    @Basic
    @Column(name = "Error Number")
    public Integer getErrorNumber() {
        return errorNumber;
    }

    public void setErrorNumber(Integer errorNumber) {
        this.errorNumber = errorNumber;
    }

    @Basic
    @Column(name = "Category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Basic
    @Column(name = "Object Type")
    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    @Basic
    @Column(name = "Description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "Context")
    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    @Basic
    @Column(name = "Created")
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        USysApplicationLogEntity that = (USysApplicationLogEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (sourceObject != null ? !sourceObject.equals(that.sourceObject) : that.sourceObject != null) return false;
        if (dataMacroInstanceId != null ? !dataMacroInstanceId.equals(that.dataMacroInstanceId) : that.dataMacroInstanceId != null)
            return false;
        if (errorNumber != null ? !errorNumber.equals(that.errorNumber) : that.errorNumber != null) return false;
        if (category != null ? !category.equals(that.category) : that.category != null) return false;
        if (objectType != null ? !objectType.equals(that.objectType) : that.objectType != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (context != null ? !context.equals(that.context) : that.context != null) return false;
        if (created != null ? !created.equals(that.created) : that.created != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (sourceObject != null ? sourceObject.hashCode() : 0);
        result = 31 * result + (dataMacroInstanceId != null ? dataMacroInstanceId.hashCode() : 0);
        result = 31 * result + (errorNumber != null ? errorNumber.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (objectType != null ? objectType.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (context != null ? context.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        return result;
    }
}
