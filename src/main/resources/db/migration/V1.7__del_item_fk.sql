SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- delete fk
-- ----------------------------
ALTER TABLE sale_order_detail DROP FOREIGN KEY sale_order_detail_ibfk_2;

SET FOREIGN_KEY_CHECKS=1;