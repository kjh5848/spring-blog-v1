insert into user_tb(username, password, email, created_at) values('ssar', '$2a$10$ipICSuyArY6TP/YK0u0u/e8afZR/AP97qOhlifg.MrLnedthKPznm', 'ssar@nate.com', now());
insert into user_tb(username, password, email, created_at) values('cos', '$2a$10$ipICSuyArY6TP/YK0u0u/e8afZR/AP97qOhlifg.MrLnedthKPznm', 'cos@nate.com', now());

insert into board_tb(title, content, user_id, created_at) values('제목1', '내용1', 1, now());
insert into board_tb(title, content, user_id, created_at) values('제목2', '내용2', 1, now());
insert into board_tb(title, content, user_id, created_at) values('제목3', '내용3', 1, now());
insert into board_tb(title, content, user_id, created_at) values('제목4', '내용4', 2, now());

insert into reply_tb(board_id, user_id, comment, created_at)values (1, 1, '나 너 싫어', now());
insert into reply_tb(board_id, user_id, comment, created_at)values (2, 1, '나 너 좋아', now());
insert into reply_tb(board_id, user_id, comment, created_at)values (3, 1, '나 너 꺼려', now());
insert into reply_tb(board_id, user_id, comment, created_at)values (4, 2, '나 너 좋아좋아', now());
