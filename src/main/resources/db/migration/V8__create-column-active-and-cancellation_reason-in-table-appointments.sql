alter table appointments add active tinyint;
alter table appointments add cancellation_reason varchar(100);
update appointments set active = true;


