# 校园公告/活动发布系统

## 一、需求分析

本系统面向校园用户，提供公告与活动的发布、浏览、管理等功能。主要用户分为：管理员、普通用户（学生/老师）。

### 主要功能模块
1. 用户管理模块
   - 用户注册、登录、角色分配、信息修改
2. 公告管理模块
   - 公告发布、编辑、删除、浏览、置顶
3. 活动管理模块
   - 活动发布、报名、浏览、评论
4. 评论与互动模块
   - 公告/活动评论、点赞
5. 系统管理模块
   - 数据备份、日志管理、用户管理模块

### 主要页面
- 登录/注册页
- 公告列表页、公告详情页
- 活动列表页、活动详情页、活动报名页
- 用户中心页
- 管理后台页

## 二、数据库设计

### ER图说明
- 用户（User）
- 公告（Announcement）
- 活动（Event）
- 评论（Comment）
- 活动报名（EventRegistration）

### 表结构设计

#### 用户表 user
| 字段名      | 类型         | 说明         |
| ----------- | ------------| ------------|
| id          | BIGINT      | 主键        |
| username    | VARCHAR(50) | 用户名      |
| password    | VARCHAR(100)| 密码        |
| role        | VARCHAR(20) | 角色        |
| email       | VARCHAR(100)| 邮箱        |
| create_time | DATETIME    | 创建时间    |

#### 公告表 announcement
| 字段名      | 类型         | 说明         |
| ----------- | ------------| ------------|
| id          | BIGINT      | 主键        |
| title       | VARCHAR(100)| 标题        |
| content     | TEXT        | 内容        |
| author_id   | BIGINT      | 发布人      |
| is_top      | TINYINT     | 是否置顶    |
| create_time | DATETIME    | 发布时间    |

#### 活动表 event
| 字段名      | 类型         | 说明         |
| ----------- | ------------| ------------|
| id          | BIGINT      | 主键        |
| title       | VARCHAR(100)| 活动标题    |
| description | TEXT        | 活动描述    |
| location    | VARCHAR(100)| 地点        |
| start_time  | DATETIME    | 开始时间    |
| end_time    | DATETIME    | 结束时间    |
| author_id   | BIGINT      | 发布人      |
| create_time | DATETIME    | 发布时间    |

#### 评论表 comment
| 字段名      | 类型         | 说明         |
| ----------- | ------------| ------------|
| id          | BIGINT      | 主键        |
| user_id     | BIGINT      | 评论人      |
| target_type | VARCHAR(20) | 评论对象类型|
| target_id   | BIGINT      | 评论对象ID  |
| content     | TEXT        | 评论内容    |
| create_time | DATETIME    | 评论时间    |

#### 活动报名表 event_registration
| 字段名      | 类型         | 说明         |
| ----------- | ------------| ------------|
| id          | BIGINT      | 主键        |
| event_id    | BIGINT      | 活动ID      |
| user_id     | BIGINT      | 用户ID      |
| register_time| DATETIME   | 报名时间    |

#### 评论点赞表 comment_like
| 字段名      | 类型         | 说明         |
| ----------- | ------------| ------------|
| id          | BIGINT      | 主键        |
| comment_id  | BIGINT      | 评论ID      |
| user_id     | BIGINT      | 用户ID      |
| create_time | DATETIME    | 点赞时间    |

## 三、开发流程
1. 需求分析：确定功能模块和主要页面
2. 数据库设计：ER图、表结构
3. 搭建项目框架：Spring Boot + MyBatis + Thymeleaf
4. 实现各功能模块：前后端一体
5. 测试与优化
6. 撰写项目文档

## 四、项目结构
```
campus-announcement-system/
├── src/
│   ├── main/
│   │   ├── java/com/campus/announcement/
│   │   │   ├── resources/
│   │   │   │   ├── templates/
│   │   │   │   ├── static/
│   │   │   │   ├── mapper/
│   │   │   │   └── application.properties
│   └── test/java/com/campus/announcement/
├── pom.xml
└── README.md
```

## 五、后续开发
- 按照模块逐步实现Controller、Service、Mapper、Model、前端页面
- 详细文档和代码注释 