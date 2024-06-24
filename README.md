Đồ án java lần này đã có thể tách làm 2 phần.
Mục đích là sẽ tách ra làm 2 pj khác nhau trong cùng đồ án này, user có thể sử dụng pj1 ( pj chỉ cho user xài vì pj này không có quyền tương tác trực tiếp với database mà phải tương tác thông qua client 
và gửi đến server ) 
PJ1 có thể sử dụng để kết nối với server với điều kiện xài chung IP mạng nên 2 pj này mặc dù hoàn toàn độc lập nhưng vẫn có thể tương tác được với nhau.
PJ2 của admin dùng có thể tương tác được với server vì có sỡ hữu packet DAO, cũng như có thêm chức năng delete thông tin trong db.
Mặc dù chưa tối ưu được chức năng có sự cho phép của admin để thay đổi cũng như mã hóa nhưng sẽ cố gắng hoàn thành trong thời gian tới !
