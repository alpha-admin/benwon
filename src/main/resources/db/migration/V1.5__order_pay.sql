SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for 增加欄位
-- ----------------------------
ALTER TABLE sale_order ADD consumption_amount INT NOT NULL DEFAULT 0 COMMENT '消費金額';
ALTER TABLE sale_order ADD pay_state INT NOT NULL DEFAULT 0 COMMENT '付款狀態';

SET FOREIGN_KEY_CHECKS=1;