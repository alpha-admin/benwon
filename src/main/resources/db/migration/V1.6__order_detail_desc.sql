SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for 增加欄位
-- ----------------------------
ALTER TABLE sale_order_detail ADD sales_unit_desc varchar(20) NOT NULL DEFAULT '' COMMENT '銷售單位說明';

SET FOREIGN_KEY_CHECKS=1;