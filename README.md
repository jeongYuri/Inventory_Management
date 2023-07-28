# 💼Inventory_Management

## Description
📌2020 programming foundation project                                                                                                            
  🕰️2020.11 ~ 2020.12
  
## System 
- 수정 : update(name, number, code);
 pstmtUpdate.executeUpdate();
pstmtUpdate = conn.prepareStatement("update supervision set name = ?, number = ? where code = ?");

- 삭제 : insert(code, name, number);
 pstmtAdd.executeUpdate();
pstmtAdd = conn.prepareStatement("insert into supervision values(?,?,?)");

- 조회 : delete(code);
pstmtDel.executeUpdate();
pstmtDel = conn.prepareStatement("delete from supervision where code = ?");

- DB연동 : private String connetionUrl = "jdbc:mysql://localhost:3306/yuri?characterEncoding=UTF-8&serverTimezone=UTC";
  
- 초기화 :  textField_1.requestFocus();
 



### Main Function
-My Sql과 연결하여 재고 삭제 및 초기화, 수정이 가능하도록 구현                                                                                                                                        

## About Project                                                                                                                          
<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">  <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> 


### Result
<img src="https://github.com/jeongYuri/Inventory_Management/assets/74125993/3f4f016d-c6d0-4732-8c57-687d000cba6f.png"  width="500" height="450"/>
<img src="https://github.com/jeongYuri/Inventory_Management/assets/74125993/403da9c6-3c0f-4f57-b9ce-295f9b43ce2d.png"  width="300" height="200"/>
              
