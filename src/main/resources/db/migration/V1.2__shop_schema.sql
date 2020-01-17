SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for 使用者資料
-- ----------------------------
CREATE TABLE user_profile (
  id varchar(60) NOT NULL COMMENT 'uid',
  real_name varchar(100) NULL COMMENT '姓名',
  nick_name varchar(100) NULL COMMENT '暱稱',
  picture varchar(300) NULL COMMENT '頭像',
  line_id varchar(100) NULL COMMENT 'Line ID',
  line_user_id varchar(100) NULL COMMENT 'Line User ID',
  line_user_name varchar(100) NULL COMMENT 'Line 使用者名稱',
  mobile_phone varchar(20) NULL COMMENT '手機',
  email varchar(200) NULL COMMENT '信箱',
  county varchar(20) NULL COMMENT '縣市',
  district varchar(20) NULL COMMENT '市區',
  zipcode varchar(10) NULL COMMENT '郵遞區號',
  address varchar(200) NULL COMMENT '地址',
  finished INT NOT NULL COMMENT '是否完成',
  created_by varchar(60) NOT NULL,
  created_date datetime NOT NULL,
  last_modified_by varchar(60) NOT NULL,
  last_modified_date datetime NOT NULL,
  PRIMARY KEY ( id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='使用者資料';

-- ----------------------------
-- Table structure for 上傳圖檔
-- ----------------------------
CREATE TABLE images_upload (
  id bigint NOT NULL COMMENT '圖片ID',
  bucket_name varchar(50) NOT NULL COMMENT '雲端位置',
  file_name varchar(200) NOT NULL COMMENT '雲端檔名',
  is_original INT NOT NULL COMMENT '是否是原始檔',
  is_default_size INT NOT NULL COMMENT '是否是預設的圖片',
  ref_image_id bigint DEFAULT NULL COMMENT '不是原始的會有參考圖',
  resource_url varchar(300) NOT NULL COMMENT 'URL 位置',
  upload_channel varchar(30) NOT NULL COMMENT '上傳的進入點',
  image_resize_def INT NOT NULL COMMENT '1x 1 倍',
  image_width INT NOT NULL COMMENT '圖片寬',
  image_size_kb float8 NOT NULL COMMENT '圖片大小 KB',
  created_by varchar(60) NOT NULL,
  created_date datetime NOT NULL,
  last_modified_by varchar(60) NOT NULL,
  last_modified_date datetime NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='上傳圖檔';

CREATE TABLE images_mapping (
  id bigint NOT NULL AUTO_INCREMENT COMMENT '流水ID',
  image_id bigint NOT NULL COMMENT '圖片ID',
  ref_table varchar(200) NOT NULL COMMENT '參照的表格',
  ref_id varchar(200) NOT NULL COMMENT '參照的物件ID',
  created_by varchar(60) NOT NULL,
  created_date datetime NOT NULL,
  last_modified_by varchar(60) NOT NULL,
  last_modified_date datetime NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='圖檔使用參考表';
ALTER TABLE images_mapping ADD FOREIGN KEY ( image_id ) REFERENCES images_upload ( id );

-- ----------------------------
-- Table structure for 商品表
-- ----------------------------
CREATE TABLE product (
  id varchar(36) NOT NULL COMMENT '商品ID',
  product_name varchar(100) NOT NULL COMMENT '品名',
  product_img_id bigint NOT NULL COMMENT '列表圖',
  product_img_resource_url varchar(300) NOT NULL COMMENT '列表圖網址',
  sale_price INT NOT NULL COMMENT '售價',
  disc_price INT NOT NULL COMMENT '優惠價',
  expiry_date date NOT NULL COMMENT '賞味期限 ex: 2020/01/01',
  basic_unit_desc varchar(20) NOT NULL COMMENT '基礎單位描述 ex: 包 件',
  norm text NOT NULL COMMENT '規範',
  product_introduction text NOT NULL COMMENT '商品介紹',
  introduction text NOT NULL COMMENT '介紹',
  specifications text NOT NULL COMMENT '規格',
  created_by varchar(60) NOT NULL,
  created_date datetime  NOT NULL,
  last_modified_by varchar(60) NOT NULL,
  last_modified_date datetime  NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='商品表';

-- ----------------------------
-- Table structure for 商品品項
-- ----------------------------
CREATE TABLE product_item (
  id varchar(36) NOT NULL COMMENT '流水ID',
  product_id varchar(36) NOT NULL COMMENT '商品 ID',
  product_item_name varchar(100) NOT NULL COMMENT '品名',
  stock_quantity INT NOT NULL COMMENT '庫存數量 ex: 99',
  sorting INT NOT NULL COMMENT '順序',
  created_by varchar(60) NOT NULL,
  created_date datetime  NOT NULL,
  last_modified_by varchar(60) NOT NULL,
  last_modified_date datetime  NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='商品品項表';
ALTER TABLE product_item ADD FOREIGN KEY ( product_id ) REFERENCES product ( id );

-- ----------------------------
-- Table structure for 商品圖片表
-- ----------------------------
CREATE TABLE product_image_mapping (
  id bigint NOT NULL AUTO_INCREMENT COMMENT '流水ID',
  product_id varchar(36) NOT NULL COMMENT '商品 ID',
  image_id bigint NOT NULL COMMENT '商品圖 ID',
  sorting INT NOT NULL COMMENT '商品圖順序',
  resource_url varchar(300) NOT NULL COMMENT '資源網址',
  created_by varchar(60) NOT NULL,
  created_date datetime NOT NULL,
  last_modified_by varchar(60) NOT NULL,
  last_modified_date datetime NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='商品圖片表';
ALTER TABLE product_image_mapping ADD FOREIGN KEY ( product_id ) REFERENCES product ( id );

-- ----------------------------
-- Table structure for 網站入口銷售
-- ----------------------------
CREATE TABLE sale_portal (
  id varchar(36) NOT NULL COMMENT '流水編號',
  product_id varchar(36) NOT NULL COMMENT '商品編號',
  sorting INT NOT NULL COMMENT '顯示順序',
  sale_start_date datetime NOT NULL COMMENT '銷售開始時間',
  sale_end_date datetime NOT NULL COMMENT '銷售結束時間',
  sale_status INT NOT NULL COMMENT '銷售狀態',
  created_by varchar(60) NOT NULL,
  created_date datetime NOT NULL,
  last_modified_by varchar(60) NOT NULL,
  last_modified_date datetime NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='網站入口銷售頁';
ALTER TABLE sale_portal ADD FOREIGN KEY ( product_id ) REFERENCES product ( id );

-- ----------------------------
-- Table structure for 開團目錄頁
-- ----------------------------
CREATE TABLE sale_group (
  id varchar(36) NOT NULL COMMENT '開團編號',
  group_name varchar(100) NOT NULL COMMENT '團購名稱',
  group_img_id bigint NOT NULL COMMENT '開團圖',
  group_img_resource_url varchar(300) NOT NULL COMMENT '開團圖網址',
  sale_start_date datetime NOT NULL COMMENT '銷售開始時間',
  sale_end_date datetime NOT NULL COMMENT '銷售結束時間',
  sale_status INT NOT NULL COMMENT '銷售狀態',
  created_by varchar(60) NOT NULL,
  created_date datetime NOT NULL,
  last_modified_by varchar(60) NOT NULL,
  last_modified_date datetime NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='開團目錄頁';

-- ----------------------------
-- Table structure for 銷售目錄頁商品
-- ----------------------------
CREATE TABLE sale_group_items (
  id bigint NOT NULL AUTO_INCREMENT COMMENT '流水ID',
  sale_group_id varchar(36) NOT NULL COMMENT '目錄頁名稱',
  product_id varchar(36) NOT NULL COMMENT '商品編號',
  sorting INT NOT NULL COMMENT '順序',
  created_by varchar(60) NOT NULL,
  created_date datetime NOT NULL,
  last_modified_by varchar(60) NOT NULL,
  last_modified_date datetime NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='銷售目錄頁商品';
ALTER TABLE sale_group_items ADD FOREIGN KEY ( sale_group_id ) REFERENCES sale_group ( id );
ALTER TABLE sale_group_items ADD FOREIGN KEY ( product_id ) REFERENCES product ( id );

-- ----------------------------
-- Table structure for 銷售訂單表
-- ----------------------------
CREATE TABLE sale_order (
  id varchar(36) NOT NULL COMMENT '訂單編號',
  purchaser_user_id varchar(36) NOT NULL COMMENT '購買人ID',
  receiver_name varchar(100) NOT NULL COMMENT '收件人姓名',
  mobile_phone varchar(20) NOT NULL COMMENT '手機',
  county varchar(20) NULL COMMENT '縣市',
  district varchar(20) NULL COMMENT '市區',
  zipcode varchar(10) NOT NULL COMMENT '郵遞區號',
  receiver_address varchar(100) NOT NULL COMMENT '收件人地址',
  remark varchar(200) COMMENT '備註',
  order_amount INT NOT NULL COMMENT '訂單金額',
  freight_amount INT NOT NULL COMMENT '運費',
  is_shipped INT NOT NULL COMMENT '是否已出貨',
  show_purchaser_user INT NOT NULL COMMENT '顯示購買人',
  created_by varchar(60) NOT NULL,
  created_date datetime NOT NULL,
  last_modified_by varchar(60) NOT NULL,
  last_modified_date datetime NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='銷售訂單表';
-- ALTER TABLE sale_order ADD FOREIGN KEY ( purchaser_user_id ) REFERENCES user_account ( id );

-- ----------------------------
-- Table structure for 訂單明細表
-- ----------------------------
CREATE TABLE sale_order_detail (
  id bigint NOT NULL AUTO_INCREMENT COMMENT '流水ID',
  order_id varchar(36) NOT NULL COMMENT '訂單編號',
  product_id varchar(36) NOT NULL COMMENT '商品編號',
  product_name varchar(100) NOT NULL COMMENT '品名',
  product_item_id varchar(36) NOT NULL COMMENT '商品品項編號',
  product_item_name varchar(100) NOT NULL COMMENT '品名',
  sales_unit INT NOT NULL COMMENT '銷售單位',
  unit_count INT NOT NULL COMMENT '購買數量',
  subtotal INT NOT NULL COMMENT '小計',
  is_shipped INT NOT NULL COMMENT '是否已出貨',
  ref_sale_group_id varchar(36) DEFAULT NULL COMMENT '跟著哪一團的',
  created_by varchar(60) NOT NULL,
  created_date datetime NOT NULL,
  last_modified_by varchar(60) NOT NULL,
  last_modified_date datetime NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='訂單明細表';
ALTER TABLE sale_order_detail ADD FOREIGN KEY ( product_id ) REFERENCES product ( id );
ALTER TABLE sale_order_detail ADD FOREIGN KEY ( product_item_id ) REFERENCES product_item ( id );

SET FOREIGN_KEY_CHECKS=1;