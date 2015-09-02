-- 注意表字段的长度应该大于插入的长度
insert into users(username, password, password_salt) values('star45', '$shiro1$SHA-512$1$$x61Ey612Kl2gpFL56FT9weDnpSo4AV8j8+qx2AuTHdRyY036xxzTTrw10Wq3+4qQyB+XURPWx1ONxp3Y3pB37A==', null);
insert into users(username, password, password_salt) values('xulikang', 'a9a114054aa6758184314fbb959fbda4', '24520ee264eab73ec09451d0e9ea6aac');