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

Date: 2014-11-30 19:19:34
*/


-- ----------------------------
-- Table structure for T_USER_BASIC
-- ----------------------------
DROP TABLE "OA"."T_USER_BASIC";
CREATE TABLE "OA"."T_USER_BASIC" (
"ID" VARCHAR2(50 BYTE) NOT NULL ,
"BIRTHDAY" DATE NULL ,
"EMAIL" VARCHAR2(50 BYTE) NULL ,
"ID_NUM" VARCHAR2(30 BYTE) NULL ,
"MARITAL_STATUS" NUMBER(10) NULL ,
"NATION" NUMBER(10) NULL ,
"NATIVE_PLACE" VARCHAR2(200 BYTE) NULL ,
"PHONE" VARCHAR2(30 BYTE) NULL ,
"POLITICAL_STATUS" NUMBER(10) NULL ,
"USER_AGE" NUMBER(10) NULL ,
"USER_NAME" VARCHAR2(50 BYTE) NOT NULL ,
"USER_SEX" NUMBER(10) NOT NULL ,
"WORK_ID" VARCHAR2(50 BYTE) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of T_USER_BASIC
-- ----------------------------
INSERT INTO "OA"."T_USER_BASIC" VALUES ('e5720eb3-bd95-45d0-9d78-2a4129b16412', TO_DATE('2014-11-29 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'allwhiteonly@gamil.com', '371523199309260014', '0', '1', '我的家在东北', '13370978384', '0', '18', '卡特琳娜', '2', '0001');
INSERT INTO "OA"."T_USER_BASIC" VALUES ('6ebf9d65-6703-4be2-a09f-7ff9877fbdb3', TO_DATE('1993-10-14 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'allwhiteonly@gamil.com', '371523199309260014', '0', '5', '我的家在东北', '13370978384', '0', '22', '无双剑姬', '2', '0001');
INSERT INTO "OA"."T_USER_BASIC" VALUES ('a70e0458-90c2-4c92-8269-a389b15fb43c', TO_DATE('1993-08-26 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'allwhiteonly@gamil.com', '371523199309260014', '0', '1', '我的家在东北', '13370978384', '0', '20', '法外狂徒', '1', '0001');
INSERT INTO "OA"."T_USER_BASIC" VALUES ('c6625273-c02c-493c-a4b6-f3bcf9ed5ee4', TO_DATE('1993-10-26 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'allwhiteonly@gamil.com', '371523199309260014', '0', '1', '我的家在东北', '13370978384', '0', '16', '寒冰射手', '2', '0001');
INSERT INTO "OA"."T_USER_BASIC" VALUES ('1e708a12-eecd-4fdc-95ec-9e7b7d09c7b8', TO_DATE('1993-10-26 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'allwhiteonly@gamil.com', '371523199309260014', '0', '1', '我的家在东北', '13370978384', '0', '22', '战争之影', '1', '0001');
INSERT INTO "OA"."T_USER_BASIC" VALUES ('04700737-daf3-450c-8e75-b2a5768d0fcb', TO_DATE('1993-10-26 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'allwhiteonly@gamil.com', '371523199309260014', '0', '3', '我的家在东北', '13370978384', '0', '22', '探险家', '1', '0001');
INSERT INTO "OA"."T_USER_BASIC" VALUES ('912593f4-c96d-4623-a600-d690f258e22d', TO_DATE('1993-10-26 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'allwhiteonly@gamil.com', '371523199309260014', '0', '4', '我的家在东北', '13370978384', '0', '22', '赏金猎人', '2', '0001');

-- ----------------------------
-- Indexes structure for table T_USER_BASIC
-- ----------------------------

-- ----------------------------
-- Checks structure for table T_USER_BASIC
-- ----------------------------
ALTER TABLE "OA"."T_USER_BASIC" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "OA"."T_USER_BASIC" ADD CHECK ("USER_NAME" IS NOT NULL);
ALTER TABLE "OA"."T_USER_BASIC" ADD CHECK ("USER_SEX" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table T_USER_BASIC
-- ----------------------------
ALTER TABLE "OA"."T_USER_BASIC" ADD PRIMARY KEY ("ID");
