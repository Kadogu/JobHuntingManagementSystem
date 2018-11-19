insert into official_absence_contents (official_absence_contents_id,contents) value(1,"説明会");
insert into official_absence_contents (official_absence_contents_id,contents) value(2,"一次試験");
insert into official_absence_contents (official_absence_contents_id,contents) value(3,"二次試験");
insert into official_absence_contents (official_absence_contents_id,contents) value(4,"三次試験");
insert into official_absence_contents (official_absence_contents_id,contents) value(5,"合同説明会");
insert into official_absence_contents (official_absence_contents_id,contents) value(6,"内定式");
insert into official_absence_contents (official_absence_contents_id,contents) value(7,"その他");
insert into official_absence_contents (official_absence_contents_id,contents) value(8,"学校案内");

insert into document_contents (document) value("履歴書");
insert into document_contents (document) value("卒業見込証明書");
insert into document_contents (document) value("成績証明書");
insert into document_contents (document) value("健康診断書");
insert into document_contents (document) value("推薦書");
insert into document_contents (document) value("その他");

insert into belongs (belongs_id, belongs_name) value("s","システム科");
insert into belongs (belongs_id, belongs_name) value("b","ビジネス科");
insert into belongs (belongs_id, belongs_name) value("d","デザイン科");
insert into belongs (belongs_id, belongs_name) value("e","就職課");

insert into department (department_id, department_name,number) value('itsys',"情報システム",1);
insert into department (department_id, department_name,number) value('nwsq',"ネットワークセキュリティ",1);
insert into department (department_id, department_name,number) value('sysen',"システム工学",1);
insert into department (department_id, department_name,number) value('itex',"ITエキスパート",1);
insert into department (department_id, department_name,number) value('itbis',"情報ビジネス",2);
insert into department (department_id, department_name,number) value('trbis',"会計ビジネス",2);
insert into department (department_id, department_name,number) value('desig',"デザイン",3);
insert into department (department_id, department_name,number) value('mulde',"総合デザイン",3);

insert into course (course_id,course_name,year,number,department_id, belongs_id) value('se',"システムエンジニア",2,1,"itsys","s");
insert into course (course_id,course_name,year,number,department_id, belongs_id) value('app',"スマートフォンアプリ開発",2,1,"itsys","s");
insert into course (course_id,course_name,year,number,department_id, belongs_id) value('ne',"ネットワークエンジニア",2,2,"nwsq","s");
insert into course (course_id,course_name,year,number,department_id, belongs_id) value('secu',"情報セキュリティ",2,2,"nwsq","s");
insert into course (course_id,course_name,year,number,department_id, belongs_id) value('webp',"WEBプログラマ",3,2,"sysen","s");
insert into course (course_id,course_name,year,number,department_id, belongs_id) value('emsys',"組込みシステム",3,3,"sysen","s");
insert into course (course_id,course_name,year,number,department_id, belongs_id) value('game',"ゲームプログラマ",3,3,"sysen","s");
insert into course (course_id,course_name,year,number,department_id, belongs_id) value('itex',"ITエキスパート",4,4,"itex","s");
insert into course (course_id,course_name,year,number,department_id, belongs_id) value('itbis',"情報ビジネス",2,1,"itbis","b");
insert into course (course_id,course_name,year,number,department_id, belongs_id) value('pbbis',"公共ビジネス",2,2,"itbis","b");
insert into course (course_id,course_name,year,number,department_id, belongs_id) value('trbis',"会計ビジネス",2,3,"trbis","b");
insert into course (course_id,course_name,year,number,department_id, belongs_id) value('trex',"会計エキスパート",2,4,"trbis","b");
insert into course (course_id,course_name,year,number,department_id, belongs_id) value('graph',"グラフィックデザイン",2,1,"desig","d");
insert into course (course_id,course_name,year,number,department_id, belongs_id) value('ancom',"アニメ・マンガ",2,2,"desig","d");
insert into course (course_id,course_name,year,number,department_id, belongs_id) value('cg',"CGクリエイト",2,2,"desig","d");
insert into course (course_id,course_name,year,number,department_id, belongs_id) value('arin',"建築インテリア",2,3,"desig","d");
insert into course (course_id,course_name,year,number,department_id, belongs_id) value('mulde',"総合デザイン",3,4,"mulde","d");

insert into contact_item (item_name) value("PW変更失敗");
insert into contact_item (item_name) value("アカウントの確認失敗");
insert into contact_item (item_name) value("メール送信失敗");
insert into contact_item (item_name) value("パスコード入力エラー");
insert into contact_item (item_name) value("報告書作成失敗");
insert into contact_item (item_name) value("公欠届作成失敗");
insert into contact_item (item_name) value("届出書作成失敗");
insert into contact_item (item_name) value("その他");
