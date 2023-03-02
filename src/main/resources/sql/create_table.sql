create table if not exists tasks (
	taskId Serial primary key,
	taskName varchar(250),
	creationDate date,
	deadline date,
	status varchar(50)
)