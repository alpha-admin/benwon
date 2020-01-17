-- ----------------------------
-- Table structure for 授權申請紀錄
-- ----------------------------
CREATE TABLE auth_records (
  id varchar(60) NOT NULL COMMENT '自己產生',
  client_id varchar(60) DEFAULT NULL COMMENT '',
  response_type varchar(60) DEFAULT NULL COMMENT '',
  redirect_uri varchar(60) DEFAULT NULL COMMENT '',
  state varchar(60) DEFAULT NULL COMMENT '',
  scope varchar(500) DEFAULT NULL COMMENT '',
  nonce varchar(60) DEFAULT NULL COMMENT '',
  my_user_id varchar(100) DEFAULT NULL COMMENT 'User ID',
  my_access_token varchar(500) DEFAULT NULL COMMENT '自己 產生的 accessToken',
  my_oauth_token varchar(1000) DEFAULT NULL COMMENT '自己 產生的完整 Token',
  line_user_id varchar(100) DEFAULT NULL COMMENT 'Line User ID',
  line_access_token varchar(1000) DEFAULT NULL COMMENT 'Line 產生的 accessToken',
  line_refresh_token varchar(1000) DEFAULT NULL COMMENT 'Line 產生的完整 Token',
  auth_code varchar(60) DEFAULT NULL COMMENT '授權碼',
  received INT DEFAULT NULL COMMENT '是否已領取',
  created_by varchar(60) NOT NULL,
  created_date datetime NOT NULL,
  last_modified_by varchar(60) NOT NULL,
  last_modified_date datetime NOT NULL,
  PRIMARY KEY ( id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='授權申請紀錄';

-- ----------------------------
-- Table structure for 資源列表
-- ----------------------------
CREATE TABLE resources (
  id varchar(60) NOT NULL COMMENT '客戶端所能訪問的資源id',
  resource_label varchar(100) DEFAULT NULL COMMENT '說明標籤',
  created_by varchar(60) NOT NULL,
  created_date datetime NOT NULL,
  last_modified_by varchar(60) NOT NULL,
  last_modified_date datetime NOT NULL,
  PRIMARY KEY ( id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='資源列表';

-- ----------------------------
-- Table structure for 資源範圍
-- ----------------------------
CREATE TABLE resource_scop (
  id varchar(60) NOT NULL COMMENT '範圍代碼',
  resource_id varchar(60) NOT NULL COMMENT '對應的資源ID',
  label varchar(60) NOT NULL COMMENT '說明標籤文字',
  created_by varchar(60) NOT NULL,
  created_date datetime NOT NULL,
  last_modified_by varchar(60) NOT NULL,
  last_modified_date datetime NOT NULL,
  PRIMARY KEY ( id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='資源範圍';

-- ----------------------------
-- Table structure for OauthClient 授權客戶端
-- ----------------------------
CREATE TABLE oauth_client_details (
  id varchar(60) NOT NULL COMMENT '主鍵,必須唯一,不能為空.用於唯一標識每一個客戶端(client).',
  client_secret varchar(256) NOT NULL COMMENT 'OAuth2 client secret',
  web_server_redirect_uri varchar(256) DEFAULT NULL COMMENT '服務端pre-established的跳轉URI',
  access_token_validity INT DEFAULT NULL COMMENT '指定access token失效時長',
  refresh_token_validity INT DEFAULT NULL COMMENT '指定refresh token的有效期',
  auto_approve bit(1) NOT NULL COMMENT '是否自動授權相關範圍',
  created_by varchar(60) NOT NULL,
  created_date datetime NOT NULL,
  last_modified_by varchar(60) NOT NULL,
  last_modified_date datetime NOT NULL,
  PRIMARY KEY ( id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='授權客戶端';

-- ----------------------------
-- Table structure for 客戶端授權方式
-- ----------------------------
CREATE TABLE oauth_client_grant_types (
  id bigint NOT NULL AUTO_INCREMENT COMMENT '流水ID',
  client_id varchar(60) NOT NULL COMMENT 'OAuth2 client ID',
  grant_type varchar(100) NOT NULL COMMENT '授權類型',
  created_by varchar(60) NOT NULL,
  created_date datetime NOT NULL,
  last_modified_by varchar(60) NOT NULL,
  last_modified_date datetime NOT NULL,
  PRIMARY KEY ( id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='客戶端可用授權方式';
ALTER TABLE oauth_client_grant_types ADD UNIQUE ( client_id, grant_type );
ALTER TABLE oauth_client_grant_types ADD FOREIGN KEY ( client_id ) REFERENCES oauth_client_details ( id );

-- ----------------------------
-- Table structure for 客戶端可存取的資源
-- ----------------------------
CREATE TABLE oauth_client_resource_mapping (
  id bigint NOT NULL AUTO_INCREMENT COMMENT '流水ID',
  client_id varchar(60) NOT NULL COMMENT 'OAuth2 client ID',
  resource_id varchar(60) NOT NULL COMMENT '資源ID',
  created_by varchar(60) NOT NULL,
  created_date datetime NOT NULL,
  last_modified_by varchar(60) NOT NULL,
  last_modified_date datetime NOT NULL,
  PRIMARY KEY ( id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='客戶端可存取的資源';
ALTER TABLE oauth_client_resource_mapping ADD UNIQUE ( client_id, resource_id );
ALTER TABLE oauth_client_resource_mapping ADD FOREIGN KEY ( client_id ) REFERENCES oauth_client_details ( id );
ALTER TABLE oauth_client_resource_mapping ADD FOREIGN KEY ( resource_id ) REFERENCES resources ( id );

-- ----------------------------
-- Table structure for 角色表
-- ----------------------------
CREATE TABLE role_info (
  id varchar(60) NOT NULL COMMENT '角色代碼',
  label varchar(60) NOT NULL COMMENT '角色名稱',
  created_by varchar(60) NOT NULL,
  created_date datetime NOT NULL,
  last_modified_by varchar(60) NOT NULL,
  last_modified_date datetime NOT NULL,
  PRIMARY KEY ( id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色';

-- ----------------------------
-- Table structure for 角色對應權限範圍
-- ----------------------------
CREATE TABLE role_scop_mapping (
  id bigint NOT NULL AUTO_INCREMENT COMMENT '流水ID',
  role_id varchar(60) NOT NULL COMMENT 'RoleID',
  resource_id varchar(60) NOT NULL COMMENT '資源ID',
  scop_id varchar(60) NOT NULL COMMENT 'ScopID',
  created_by varchar(60) NOT NULL,
  created_date datetime NOT NULL,
  last_modified_by varchar(60) NOT NULL,
  last_modified_date datetime NOT NULL,
  PRIMARY KEY ( id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色對應權限範圍';
ALTER TABLE role_scop_mapping ADD UNIQUE ( role_id, scop_id );
ALTER TABLE role_scop_mapping ADD FOREIGN KEY ( role_id ) REFERENCES role_info ( id );
ALTER TABLE role_scop_mapping ADD FOREIGN KEY ( resource_id ) REFERENCES resources ( id );
ALTER TABLE role_scop_mapping ADD FOREIGN KEY ( scop_id ) REFERENCES resource_scop ( id );

-- ----------------------------
-- Table structure for 主帳號
-- ----------------------------
CREATE TABLE user_accounts (
  id varchar(60) NOT NULL COMMENT 'uid',
  user_name varchar(60) NOT NULL COMMENT '用戶帳號',
  is_enabled bit(1) NOT NULL COMMENT '是否啟用',
  is_expired bit(1) NOT NULL COMMENT '是否過期',
  is_locked bit(1) NOT NULL COMMENT '帳號是否鎖定',
  credentials_expired bit(1) NOT NULL COMMENT '證書是否過期',
  created_by varchar(60) NOT NULL,
  created_date datetime NOT NULL,
  last_modified_by varchar(60) NOT NULL,
  last_modified_date datetime NOT NULL,
  PRIMARY KEY ( id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='主帳號';

-- ----------------------------
-- Table structure for benwon 帳號
-- ----------------------------
CREATE TABLE user_benwon (
  id varchar(60) NOT NULL COMMENT 'uid',
  user_password varchar(60) NOT NULL  COMMENT '用戶密碼',
  created_by varchar(60) NOT NULL,
  created_date datetime NOT NULL,
  last_modified_by varchar(60) NOT NULL,
  last_modified_date datetime NOT NULL,
  PRIMARY KEY ( id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='benwon 帳號';
ALTER TABLE user_benwon ADD FOREIGN KEY ( id ) REFERENCES user_accounts ( id );

-- ----------------------------
-- Table structure for Line 用戶資料
-- ----------------------------
CREATE TABLE user_line (
  id bigint NOT NULL AUTO_INCREMENT COMMENT '流水ID',
  line_id varchar(60) NOT NULL COMMENT 'line userid',
  user_id varchar(60) NOT NULL COMMENT 'uid',
  created_by varchar(60) NOT NULL,
  created_date datetime NOT NULL,
  last_modified_by varchar(60) NOT NULL,
  last_modified_date datetime NOT NULL,
  PRIMARY KEY ( id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Line 用戶';
ALTER TABLE user_line ADD UNIQUE ( line_id, user_id );
ALTER TABLE user_line ADD FOREIGN KEY ( user_id ) REFERENCES user_accounts ( id );

-- ----------------------------
-- Table structure for 使用者對應角色
-- ----------------------------
CREATE TABLE user_role_mapping (
  id bigint NOT NULL AUTO_INCREMENT COMMENT '流水ID',
  user_id varchar(60) NOT NULL COMMENT '帳號ID',
  role_id varchar(60) NOT NULL COMMENT '角色代碼',
  created_by varchar(60) NOT NULL,
  created_date datetime NOT NULL,
  last_modified_by varchar(60) NOT NULL,
  last_modified_date datetime NOT NULL,
  PRIMARY KEY ( id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='使用者對應角色';
ALTER TABLE user_role_mapping ADD UNIQUE ( user_id, role_id );
ALTER TABLE user_role_mapping ADD FOREIGN KEY ( user_id ) REFERENCES user_accounts ( id );
ALTER TABLE user_role_mapping ADD FOREIGN KEY ( role_id ) REFERENCES role_info ( id );

-- ----------------------------
-- Table structure for 核發Token列表 and 更新Token列表
-- https://github.com/spring-projects/spring-security-oauth/blob/master/spring-security-oauth2/src/test/resources/schema.sql
-- ----------------------------
CREATE TABLE oauth_access_token (
  token_id varchar(256),
  token blob,
  authentication_id varchar(256),
  user_name varchar(256),
  client_id varchar(256),
  authentication blob,
  refresh_token varchar(256),
  PRIMARY KEY ( authentication_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='核發Token列表';

CREATE TABLE oauth_refresh_token (
  token_id VARCHAR(256),
  token blob,
  authentication blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='更新Token列表';
