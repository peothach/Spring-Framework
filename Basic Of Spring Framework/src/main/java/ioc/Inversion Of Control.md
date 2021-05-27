## Inversion Of Control
***Dependency Injection*** giúp chúng ta dễ dàng mở rộng code và giảm sự phụ thuộc giữa các dependency với nhau. Tuy nhiên, lúc này, khi code bạn sẽ phải kiêm thêm nhiệm vụ Inject dependency (tiêm sự phụ thuộc). Thử tưởng tượng một Class có hàng chục dependency thì bạn sẽ phải tự tay inject từng ý cái. Việc này lại dẫn tới khó khăn trong việc code, quản lý code và dependency

```java
public static void main(String[] args) {
    Outfit bikini = new Bikini();
    Accessories gucci = new GucciAccessories();
    HairStyle hair = new KoreanHairStyle();
    Girl ngocTrinh = new Girl(bikini, gucci, hair);
}
```
Giá như lúc này có thằng làm hộ được chúng ta việc này thì tốt biết mấy.

Bây giờ giả sử, chúng ta định nghĩa trước toàn bộ các dependency có trong Project, mô tả nó và tống nó vào 1 cái kho và giao cho một thằng tên là framework quản lý. Bất kỳ các Class nào khi khởi tạo, nó cần dependency gì, thì cái framework này sẽ tự tìm trong kho rồi inject vào đối tượng thay chúng ta. sẽ tiện hơn phải không?

![](https://github.com/peothach/Spring-Framework/blob/master/image/Ioc-ngoctrinh.jpg)

That it, chính nó, đó cũng chính là nguyên lý chính của Inversion of Control (IOC) - Đảo chiều sự điều khiển

Nguyên văn Wiki:
> Inversion of Control is a programming principle. flow of control within the application is not controlled by the application itself, but rather by the underlying framework.

Khi đó, code chúng ta sẽ chỉ cần như này, để lấy ra 1 đối tượng:

```java
@Override
public void run(String... args) throws Exception {
    Girl girl = context.getBean(Girl.class);
}
```