package com.example.sqlinjectionexam.repository;

import com.example.sqlinjectionexam.model.Setting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface SettingRepository extends JpaRepository<Setting, BigInteger> {
    Setting findSettingBySettingKey(String settingKey);
}
