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

Date: 2014-11-30 19:18:23
*/


-- ----------------------------
-- Table structure for T_ACCOUNT
-- ----------------------------
DROP TABLE "OA"."T_ACCOUNT";
CREATE TABLE "OA"."T_ACCOUNT" (
"ID" VARCHAR2(50 BYTE) NOT NULL ,
"ACCOUNT_NAME" VARCHAR2(35 BYTE) NOT NULL ,
"ACCOUNT_PWD" VARCHAR2(35 BYTE) NOT NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of T_ACCOUNT
-- ----------------------------
INSERT INTO "OA"."T_ACCOUNT" VALUES ('2716fc17-cc56-44bf-99b1-ae51a4eda5e4', 'admin', '21232f297a57a5a743894a0e4a801fc3');

-- ----------------------------
-- Indexes structure for table T_ACCOUNT
-- ----------------------------

-- ----------------------------
-- Checks structure for table T_ACCOUNT
-- ----------------------------
ALTER TABLE "OA"."T_ACCOUNT" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "OA"."T_ACCOUNT" ADD CHECK ("ACCOUNT_NAME" IS NOT NULL);
ALTER TABLE "OA"."T_ACCOUNT" ADD CHECK ("ACCOUNT_PWD" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table T_ACCOUNT
-- ----------------------------
ALTER TABLE "OA"."T_ACCOUNT" ADD PRIMARY KEY ("ID");
