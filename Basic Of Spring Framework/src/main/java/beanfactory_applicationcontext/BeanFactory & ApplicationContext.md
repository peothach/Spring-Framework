## BeanFactory & ApplicationContext
> BeanFactory và ApplicationContext đều là các interface thực hiện IoC Container. ApplicationContext được xây dựng từ BeanFactory nhưng nó có thêm một số chức năng mở rộng như tích hợp với Spring AOP, xử lý message, context cho web application.

## Sự khác nhau chính của BeanFactory và ApplicationContext
- BeanFactory : Các bean được tạo ra khi chúng ta gọi phương thức getBean()
- ApplicationContext : chúng ta không cần phải chờ phương thức getBean được gọi mới tạo Bean. Mà khi container được start (khởi động) thì bean cũng đã được tạo ra do vậy không phải chờ gọi phương thức getBean.

## Image
![](https://github.com/peothach/Spring-Framework/blob/master/image/BeanFacctory%20-%20ApplicationContext.jpg)