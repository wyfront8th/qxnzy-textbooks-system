#教材信息
CREATE TABLE textbook_tb (
    xxdm VARCHAR(20) NOT NULL, -- 学校代码
    cbh VARCHAR(20) NOT NULL, -- 出版号 (对应 ISBN)
    jcmc VARCHAR(255) NOT NULL, -- 教材名称
    jclx VARCHAR(100), -- 教材类型
    bc INT, -- 版次
    cbs VARCHAR(255), -- 出版社
    bzrs INT, -- 编者总数
    cbrq DATE, -- 出版日期
    zz VARCHAR(255), -- 作者
    flh VARCHAR(50), -- 分类号（中国图书馆分类法分类）
    dj DECIMAL(10, 2), -- 定价
    sfgjghjc TINYINT(1), -- 是否国家规划教材 (1: 是, 0: 否)
    ghjcpc VARCHAR(50), -- 规划教材批次
    sfxqhzkfjc TINYINT(1), -- 是否校企合作开发教程 (1: 是, 0: 否)
    sjcjr VARCHAR(100), -- 数据采集人
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (xxdm, cbh) -- 复合主键
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

#部门信息
CREATE TABLE xbxx_tb (
    bmbm VARCHAR(20) NOT NULL, -- 部门编码
    bmmc VARCHAR(100) NOT NULL, -- 部门名称
    xxmc VARCHAR(100) NOT NULL, -- 学校名称
    dwzn VARCHAR(255), -- 单位职能
    sfkkdw TINYINT(1), -- 是否开课单位 (1: 是, 0: 否)
    PRIMARY KEY (bmbm) -- 部门编码作为主键
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

#专业信息
CREATE TABLE zyxx_tb (
    bmmc VARCHAR(100) NOT NULL, -- 部门名称
    zymc VARCHAR(100) NOT NULL, -- 专业名称
    zydm VARCHAR(20) NOT NULL, -- 专业代码
    zycl VARCHAR(50), -- 专业层次
    xz INT, -- 学制 (以年为单位)
    zydl VARCHAR(50), -- 专业大类
    zyl VARCHAR(50), -- 专业类
    PRIMARY KEY (zydm) -- 专业代码作为主键
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

#班级信息
CREATE TABLE bjxx_tb (
    xbmc VARCHAR(100), -- 系部名称
    zymc VARCHAR(100), -- 专业名称
    bh VARCHAR(20) NOT NULL, -- 班号
    bjmc VARCHAR(100), -- 班级名称
    rs INT, -- 人数
    fdy VARCHAR(100), -- 辅导员
    fdydh VARCHAR(20), -- 辅导员电话
    PRIMARY KEY (bh) -- 班号作为主键
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

#教师信息
CREATE TABLE jsxx_tb (
    xbmc VARCHAR(100), -- 系部名称
    jgh VARCHAR(20) NOT NULL, -- 教工号
    xm VARCHAR(100) NOT NULL, -- 姓名
    xb VARCHAR(10), -- 性别
    sfzh VARCHAR(18) UNIQUE, -- 身份证号
    csrq DATE, -- 出生日期
    zzmm VARCHAR(50), -- 政治面貌
    mz VARCHAR(50), -- 民族
    xl VARCHAR(50), -- 学历
    xw VARCHAR(50), -- 学位
    rylb VARCHAR(50), -- 人员类别
    PRIMARY KEY (jgh) -- 教工号作为主键
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

#课程信息
CREATE TABLE kcxx_tb (
    zymc VARCHAR(100), -- 专业名称
    zydm VARCHAR(20), -- 专业代码
    zycl VARCHAR(50), -- 专业层次
    xz INT, -- 学制 (以年为单位)
    pyfs VARCHAR(50), -- 培养方式
    jtxs VARCHAR(50), -- 具体形式
    sylx VARCHAR(50), -- 生源类型
    nj INT, -- 年级
    kcmc VARCHAR(100), -- 课程名称
    kcdm VARCHAR(20), -- 课程代码
    kclx VARCHAR(50), -- 课程类型
    kcshx VARCHAR(50), -- 课程属性
    ggjck ENUM('是', '否'), -- 公共基础课 ('是'或'否')
    zyxk ENUM('是', '否'), -- 专业（技能）课 ('是'或'否')
    kcxz VARCHAR(50), -- 课程性质
    kkxq INT, -- 开课学期
    kkdxm VARCHAR(100), -- 开课单位名称
    sfxqhzkk ENUM('是', '否'), -- 是否校企合作开发课程 ('是'或'否')
    hzqymc VARCHAR(100), -- 合作企业名称
    zxjpk ENUM('是', '否'), -- 在线精品课程 ('是'或'否')
    sfkzrtkc ENUM('是', '否'), -- 是否课证融通课程 ('是'或'否')
    sfwljsxk ENUM('是', '否'), -- 是否网络教学课程 ('是'或'否')
    kcszsk ENUM('是', '否'), -- 课程思政示范课 ('是'或'否')
    kczxs INT, -- 课程总学时
    sjjxxs INT, -- 实践教学学时（个）
    tbr VARCHAR(100) -- 填报人
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;