package com.nju.software.Dao2;

import com.nju.software.Bean2.CmsUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CmsUserDao extends JpaRepository<CmsUser,Long> {
}
