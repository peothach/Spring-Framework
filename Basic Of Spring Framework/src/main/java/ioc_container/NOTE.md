## DI
Trong tất cả các dự án Spring thì Spring IOC Container là trái tim của Spring. Nó có nhiệm vụ quản lý vòng đời của bean (các đối tượng trong dự án spring), khởi tạo, cấu hình, và tương tác giữa các bean trong ứng dụng Spring. Mình có thể cấu hình Spring IOC bằng XML, Java code hoặc quan Java annotation.

Bây giờ giả sử, chúng ta định nghĩa trước toàn bộ các dependency có trong Project, mô tả nó và tống nó vào 1 cái kho và giao cho một thằng tên là framework quản lý. Bất kỳ các Class nào khi khởi tạo, nó cần dependency gì, thì cái framework này sẽ tự tìm trong kho rồi inject vào đối tượng thay chúng ta. sẽ tiện hơn phải không?

That it, chính nó, đó cũng chính là nguyên lý chính của Inversion of Control (IOC) - Đảo chiều sự điều khiển.

## Image
[Link](https://github.com/peothach/Spring-Framework/blob/master/image/spring-ioc-container.png)