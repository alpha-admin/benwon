SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for 團購主邀請
-- ----------------------------
CREATE TABLE invite_code (
  id varchar(60) NOT NULL COMMENT '流水號',
  invite_code varchar(60) NULL COMMENT '邀請代碼',
  code_status INT NOT NULL COMMENT '代碼狀態',
  expiry_date date NULL COMMENT '期限 ex: 2020/01/01',
  created_by varchar(60) NOT NULL,
  created_date datetime NOT NULL,
  last_modified_by varchar(60) NOT NULL,
  last_modified_date datetime NOT NULL,
  PRIMARY KEY ( id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='團購主邀請代碼表';
ALTER TABLE invite_code ADD CONSTRAINT u_invite_code UNIQUE ( invite_code );

-- ----------------------------
-- Table structure for 簡訊發送紀錄
-- ----------------------------
CREATE TABLE sms_record (
  id varchar(60) NOT NULL COMMENT '流水號',
  phone_number varchar(20) NOT NULL COMMENT '發送電話號碼',
  verification_code varchar(20) NOT NULL COMMENT '驗證碼',
  verfy_status INT NOT NULL COMMENT '驗證狀態',
  created_by varchar(60) NOT NULL,
  created_date datetime NOT NULL,
  last_modified_by varchar(60) NOT NULL,
  last_modified_date datetime NOT NULL,
  PRIMARY KEY ( id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='簡訊發送紀錄';

SET FOREIGN_KEY_CHECKS=1;