delete from users;
delete from user_roles;
delete from roles_permissions;
insert into users(username, password, password_salt) values('star45', 'admin', null);
insert into user_roles(username, role_name) values('star45', 'role1');
insert into user_roles(username, role_name) values('star45', 'role2');
insert into roles_permissions(role_name, permission) values('role1', '+user1+10');
insert into roles_permissions(role_name, permission) values('role1', 'user1:*');
insert into roles_permissions(role_name, permission) values('role1', '+user2+10');
insert into roles_permissions(role_name, permission) values('role1', 'user2:*');