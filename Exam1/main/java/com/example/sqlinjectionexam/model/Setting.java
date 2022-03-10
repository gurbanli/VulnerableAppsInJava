package com.example.sqlinjectionexam.model;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name="settings")
public class Setting {
    @Id
    @GeneratedValue
    private BigInteger id;

    @Column(unique = true, nullable = false)
    private String settingKey;
    private String settingValue;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }
    public String getSettingKey() {
        return settingKey;
    }

    public void setSettingKey(String settingKey) {
        this.settingKey = settingKey;
    }

    public String getSettingValue() {
        return settingValue;
    }

    public void setSettingValue(String settingValue) {
        this.settingValue = settingValue;
    }

}
