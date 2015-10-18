/*
Navicat Oracle Data Transfer
Oracle Client Version : 10.2.0.5.0

Source Server         : oracle11g
Source Server Version : 110200
Source Host           : 127.0.0.1:1521
Source Schema         : OA

Target Server Type    : ORACLE
Target Server Version : 110200
File Encoding         : 65001

Date: 2014-11-30 19:18:52
*/


-- ----------------------------
-- Table structure for T_MENUINFO
-- ----------------------------
DROP TABLE "OA"."T_MENUINFO";
CREATE TABLE "OA"."T_MENUINFO" (
"ID" VARCHAR2(50 BYTE) NOT NULL ,
"MARK" VARCHAR2(500 BYTE) NULL ,
"MENU_NAME" VARCHAR2(30 BYTE) NULL ,
"MENU_TYPE" VARCHAR2(20 BYTE) NULL ,
"MENU_URL" VARCHAR2(200 BYTE) NULL ,
"PARENT_ID" VARCHAR2(50 BYTE) NULL ,
"PARETNNAME" VARCHAR2(255 BYTE) NULL ,
"VISIBILITY" CHAR(1 BYTE) NOT NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of T_MENUINFO
-- ----------------------------
INSERT INTO "OA"."T_MENUINFO" VALUES ('0d798774-a7db-4cea-aaeb-6a1fa85afd5f', '个人办公', '个人办公', null, null, null, null, 'Y');
INSERT INTO "OA"."T_MENUINFO" VALUES ('9497318e-6efb-497b-be39-ad2d90fda47b', null, '50', null, null, 'f70212e0-d905-4762-b9ca-863fcd06019e', null, 'Y');
INSERT INTO "OA"."T_MENUINFO" VALUES ('637cde42-7a2c-4634-95d9-35c73e5987af', '个人办公', '用户管理', null, '/userAction/toUserManagerPage.action', '6b4aa182-57d1-4a96-95f4-041205555c37', null, 'Y');
INSERT INTO "OA"."T_MENUINFO" VALUES ('6b4aa182-57d1-4a96-95f4-041205555c37', '系统维护', '系统维护', null, null, null, null, 'Y');
INSERT INTO "OA"."T_MENUINFO" VALUES ('f70212e0-d905-4762-b9ca-863fcd06019e', '菜单管理', '菜单管理', null, '/menuAction/toMenuManagerTabContent.action', '6b4aa182-57d1-4a96-95f4-041205555c37', '系统维护', 'Y');

-- ----------------------------
-- Indexes structure for table T_MENUINFO
-- ----------------------------

-- ----------------------------
-- Checks structure for table T_MENUINFO
-- ----------------------------
ALTER TABLE "OA"."T_MENUINFO" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "OA"."T_MENUINFO" ADD CHECK ("VISIBILITY" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table T_MENUINFO
-- ----------------------------
ALTER TABLE "OA"."T_MENUINFO" ADD PRIMARY KEY ("ID");
