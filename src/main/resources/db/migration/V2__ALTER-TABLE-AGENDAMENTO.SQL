alter table tb_agendamento add column status varchar(25) not null;
update tb_agendamento set status="PAGO"