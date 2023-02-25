create table if not exists tasks (
	taskId UUID primary key,
	taskName varchar(250),
	creationDate date,
	deadline date,
	status varchar(50)
)