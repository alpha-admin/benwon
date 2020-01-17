SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Records of resources
-- ----------------------------
INSERT INTO resources (id, resource_label, created_by, created_date, last_modified_by, last_modified_date)
VALUES ('benwon', '飯團媽', '', CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP);

-- ----------------------------
-- Records of resource_scop
-- ----------------------------
INSERT INTO resource_scop (id, resource_id, label, created_by, created_date, last_modified_by, last_modified_date)
VALUES ('image', 'benwon', '圖片管理', '', CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP);
INSERT INTO resource_scop (id, resource_id, label, created_by, created_date, last_modified_by, last_modified_date)
VALUES ('product', 'benwon', '商品管理', '', CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP);
INSERT INTO resource_scop (id, resource_id, label, created_by, created_date, last_modified_by, last_modified_date)
VALUES ('sale_group', 'benwon', '團購管理', '', CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP);
INSERT INTO resource_scop (id, resource_id, label, created_by, created_date, last_modified_by, last_modified_date)
VALUES ('sale_order', 'benwon', '訂單管理', '', CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP);
INSERT INTO resource_scop (id, resource_id, label, created_by, created_date, last_modified_by, last_modified_date)
VALUES ('user_profile', 'benwon', '帳號資訊管理', '', CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP);

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO oauth_client_details (id, client_secret, web_server_redirect_uri, access_token_validity, refresh_token_validity, auto_approve, created_by, created_date, last_modified_by, last_modified_date)
VALUES ('benwon_web', '$2a$10$c85hYXPx4niZCCkmxeqXHOriQvvaWBSd9SVpYoq2ZAbs0uUa1ESL.', NULL, '86400', '604800', b'1', '', CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP);
-- default password is 123456

-- ----------------------------
-- Records of oauth_client_grant_types
-- ----------------------------
INSERT INTO oauth_client_grant_types (client_id, grant_type, created_by, created_date, last_modified_by, last_modified_date)
VALUES ('benwon_web', 'password', '', CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP);
INSERT INTO oauth_client_grant_types (client_id, grant_type, created_by, created_date, last_modified_by, last_modified_date)
VALUES ('benwon_web', 'refresh_token', '', CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP);
INSERT INTO oauth_client_grant_types (client_id, grant_type, created_by, created_date, last_modified_by, last_modified_date)
VALUES ('benwon_web', 'authorization_code', '', CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP);
INSERT INTO oauth_client_grant_types (client_id, grant_type, created_by, created_date, last_modified_by, last_modified_date)
VALUES ('benwon_web', 'implicit', '', CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP);

-- ----------------------------
-- Records of oauth_client_resource_mapping
-- ----------------------------
INSERT INTO oauth_client_resource_mapping (client_id, resource_id, created_by, created_date, last_modified_by, last_modified_date)
VALUES ('benwon_web', 'benwon', '', CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP);

-- ----------------------------
-- Records of role_info
-- ----------------------------
INSERT INTO role_info (id, label, created_by, created_date, last_modified_by, last_modified_date)
VALUES ('sysadmin', '系統管理員', '', CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP);
INSERT INTO role_info (id, label, created_by, created_date, last_modified_by, last_modified_date)
VALUES ('group_buyer', '團購主', '', CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP);

-- ----------------------------
-- Records of role_scop_mapping
-- ----------------------------
INSERT INTO role_scop_mapping (role_id, resource_id, scop_id, created_by, created_date, last_modified_by, last_modified_date)
VALUES ('sysadmin', 'benwon', 'image', ' ', CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP);
INSERT INTO role_scop_mapping (role_id, resource_id, scop_id, created_by, created_date, last_modified_by, last_modified_date)
VALUES ('sysadmin', 'benwon', 'product', ' ', CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP);
INSERT INTO role_scop_mapping (role_id, resource_id, scop_id, created_by, created_date, last_modified_by, last_modified_date)
VALUES ('sysadmin', 'benwon', 'sale_group', ' ', CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP);
INSERT INTO role_scop_mapping (role_id, resource_id, scop_id, created_by, created_date, last_modified_by, last_modified_date)
VALUES ('sysadmin', 'benwon', 'sale_order', ' ', CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP);
INSERT INTO role_scop_mapping (role_id, resource_id, scop_id, created_by, created_date, last_modified_by, last_modified_date)
VALUES ('sysadmin', 'benwon', 'user_profile', ' ', CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP);

-- ----------------------------
-- Records of user_accounts
-- ----------------------------
INSERT INTO user_accounts (id, user_name, is_enabled, is_expired, is_locked, credentials_expired, created_by, created_date, last_modified_by, last_modified_date)
VALUES ('1', 'admin', b'1', b'0', b'0', b'0', '', CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP);


INSERT INTO user_benwon (id, user_password, created_by, created_date, last_modified_by, last_modified_date)
VALUES ('1', '$2a$10$qMwiM5mOk.uw9/Z5u1FqDur9lbTeFD6kaCPeY87JjkjxGfj6auLGO', '', CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP);
-- default password is 123456

-- ----------------------------
-- Records of user_role_mapping
-- ----------------------------
INSERT INTO user_role_mapping (user_id, role_id, created_by, created_date, last_modified_by, last_modified_date)
VALUES ('1', 'sysadmin', '', CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP);

SET FOREIGN_KEY_CHECKS=1;