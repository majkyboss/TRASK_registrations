INSERT INTO mydb.`Role` (`name`) 
	VALUES ('Administrator');
INSERT INTO mydb.`Role` (`name`) 
	VALUES ('Agent');
INSERT INTO mydb.`Role` (`name`) 
	VALUES ('Manager');

INSERT INTO mydb.`User` (`name`, lastname, degree, birth_number, date_in, date_out, role_id) 
	VALUES ('agent1', 'secondName', NULL, '1111119999', CURRENT_DATE, NULL, 2);
INSERT INTO mydb.`User` (`name`, lastname, degree, birth_number, date_in, date_out, role_id) 
	VALUES ('agent2', 'secondName', NULL, '2222229999', CURRENT_DATE, NULL, 2);
INSERT INTO mydb.`User` (`name`, lastname, degree, birth_number, date_in, date_out, role_id) 
	VALUES ('manager1', 'secondName', NULL, '3333339999', CURRENT_DATE, NULL, 3);
INSERT INTO mydb.`User` (`name`, lastname, degree, birth_number, date_in, date_out, role_id) 
	VALUES ('manager2', 'secondName', NULL, '4444449999', CURRENT_DATE, NULL, 3);
INSERT INTO mydb.`User` (`name`, lastname, degree, birth_number, date_in, date_out, role_id) 
	VALUES ('agent3', 'secondName', NULL, '5555559999', CURRENT_DATE, NULL, 2);

INSERT INTO mydb.Branch (`name`, manager_id) 
        VALUES ('branch 1', 3);
INSERT INTO mydb.Branch (`name`, manager_id) 
        VALUES ('branch 2', 4);

INSERT INTO mydb.RegState (`name`) 
	VALUES ('new');
INSERT INTO mydb.RegState (`name`) 
	VALUES ('aprooved');
INSERT INTO mydb.RegState (`name`) 
	VALUES ('denied');

INSERT INTO mydb.Unit (user_id, branch_id) 
	VALUES (1, 1);
INSERT INTO mydb.Unit (user_id, branch_id) 
	VALUES (2, 1);
INSERT INTO mydb.Unit (user_id, branch_id) 
	VALUES (5, 2);

INSERT INTO mydb.Registration (ico, company_name, reg_date, unit_user_id, unit_branch_id, regState_id) 
	VALUES ('1111111111', 'company 1', CURRENT_DATE, 1, 1, 1);
INSERT INTO mydb.Registration (ico, company_name, reg_date, unit_user_id, unit_branch_id, regState_id) 
	VALUES ('2222222222', 'company 2', CURRENT_DATE, 2, 1, 1);
INSERT INTO mydb.Registration (ico, company_name, reg_date, unit_user_id, unit_branch_id, regState_id) 
	VALUES ('3333333333', 'company 3', CURRENT_DATE, 5, 2, 1);