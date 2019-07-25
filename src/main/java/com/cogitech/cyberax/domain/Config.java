package com.cogitech.cyberax.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Config.
 */
@Entity
@Table(name = "config")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Config implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "max_mise")
    private Integer maxMise;

    @Column(name = "max_gagnant")
    private Integer maxGagnant;

    @Column(name = "url_server")
    private String urlServer;

    @Column(name = "api_key")
    private String apiKey;

    @Column(name = "api_pass")
    private String apiPass;

    @Column(name = "montant_mise")
    private Double montantMise;

    @Column(name = "gains_1")
    private Double gains1;

    @Column(name = "gains_2")
    private Double gains2;

    @Column(name = "gains_3")
    private Double gains3;

    @Column(name = "gains_4")
    private Double gains4;

    @Column(name = "gains_5")
    private Double gains5;

    @Column(name = "gains_6")
    private Double gains6;

    @Column(name = "gains_7")
    private Double gains7;

    @Column(name = "gains_8")
    private Double gains8;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMaxMise() {
        return maxMise;
    }

    public Config maxMise(Integer maxMise) {
        this.maxMise = maxMise;
        return this;
    }

    public void setMaxMise(Integer maxMise) {
        this.maxMise = maxMise;
    }

    public Integer getMaxGagnant() {
        return maxGagnant;
    }

    public Config maxGagnant(Integer maxGagnant) {
        this.maxGagnant = maxGagnant;
        return this;
    }

    public void setMaxGagnant(Integer maxGagnant) {
        this.maxGagnant = maxGagnant;
    }

    public String getUrlServer() {
        return urlServer;
    }

    public Config urlServer(String urlServer) {
        this.urlServer = urlServer;
        return this;
    }

    public void setUrlServer(String urlServer) {
        this.urlServer = urlServer;
    }

    public String getApiKey() {
        return apiKey;
    }

    public Config apiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiPass() {
        return apiPass;
    }

    public Config apiPass(String apiPass) {
        this.apiPass = apiPass;
        return this;
    }

    public void setApiPass(String apiPass) {
        this.apiPass = apiPass;
    }

    public Double getMontantMise() {
        return montantMise;
    }

    public Config montantMise(Double montantMise) {
        this.montantMise = montantMise;
        return this;
    }

    public void setMontantMise(Double montantMise) {
        this.montantMise = montantMise;
    }

    public Double getGains1() {
        return gains1;
    }

    public Config gains1(Double gains1) {
        this.gains1 = gains1;
        return this;
    }

    public void setGains1(Double gains1) {
        this.gains1 = gains1;
    }

    public Double getGains2() {
        return gains2;
    }

    public Config gains2(Double gains2) {
        this.gains2 = gains2;
        return this;
    }

    public void setGains2(Double gains2) {
        this.gains2 = gains2;
    }

    public Double getGains3() {
        return gains3;
    }

    public Config gains3(Double gains3) {
        this.gains3 = gains3;
        return this;
    }

    public void setGains3(Double gains3) {
        this.gains3 = gains3;
    }

    public Double getGains4() {
        return gains4;
    }

    public Config gains4(Double gains4) {
        this.gains4 = gains4;
        return this;
    }

    public void setGains4(Double gains4) {
        this.gains4 = gains4;
    }

    public Double getGains5() {
        return gains5;
    }

    public Config gains5(Double gains5) {
        this.gains5 = gains5;
        return this;
    }

    public void setGains5(Double gains5) {
        this.gains5 = gains5;
    }

    public Double getGains6() {
        return gains6;
    }

    public Config gains6(Double gains6) {
        this.gains6 = gains6;
        return this;
    }

    public void setGains6(Double gains6) {
        this.gains6 = gains6;
    }

    public Double getGains7() {
        return gains7;
    }

    public Config gains7(Double gains7) {
        this.gains7 = gains7;
        return this;
    }

    public void setGains7(Double gains7) {
        this.gains7 = gains7;
    }

    public Double getGains8() {
        return gains8;
    }

    public Config gains8(Double gains8) {
        this.gains8 = gains8;
        return this;
    }

    public void setGains8(Double gains8) {
        this.gains8 = gains8;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Config)) {
            return false;
        }
        return id != null && id.equals(((Config) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Config{" +
            "id=" + getId() +
            ", maxMise=" + getMaxMise() +
            ", maxGagnant=" + getMaxGagnant() +
            ", urlServer='" + getUrlServer() + "'" +
            ", apiKey='" + getApiKey() + "'" +
            ", apiPass='" + getApiPass() + "'" +
            ", montantMise=" + getMontantMise() +
            ", gains1=" + getGains1() +
            ", gains2=" + getGains2() +
            ", gains3=" + getGains3() +
            ", gains4=" + getGains4() +
            ", gains5=" + getGains5() +
            ", gains6=" + getGains6() +
            ", gains7=" + getGains7() +
            ", gains8=" + getGains8() +
            "}";
    }
}
