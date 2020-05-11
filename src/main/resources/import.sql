insert into ctc_role (id,name) values(1,'ROLE_USER');
insert into ctc_role (id,name) values(2,'ROLE_ADMIN');

insert into ctc_user(id,email,name,password,username,deleted,createdBy,createdDate,modifiedBy,modifiedDate) values(1,'rajesh@gmail.com','Rajesh','$2a$10$a40Ivd9hjvGv9FikQFb2fuqmHqYNxVKAAPTyEshw8GMzlFLFmFzDW','rajesh',0,'rajesh',current_timestamp,'rajesh',current_timestamp);

insert into ctc_user_roles (userId,roleId) values(1,1);

insert into ctc_project (id ,name ,description,deleted,createdBy,createdDate,modifiedBy,modifiedDate) values(1,'Tech Refresh 2020','Batch 1',0,'rajesh',current_timestamp,'rajesh',current_timestamp);
