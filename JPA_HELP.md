# yuyufeng
     cascade表示级联操作  
     CascadeType.MERGE级联更新：若items属性修改了那么order对象保存时同时修改items里的对象。对应EntityManager的merge方法   
     CascadeType.REFRESH级联刷新：获取order对象里也同时也重新获取最新的items时的对象。对应EntityManager的refresh(object)方法有效。即会重新查询数据库里的最新数据  
     CascadeType.PERSIST级联保存：对order对象保存时也对items里的对象也会保存。对应EntityManager的presist方法   
     CascadeType.REMOVE级联删除：对order对象删除也对items里的对象也会删除。对应EntityManager的remove方法 
## 达条件查询的关键字
      And --- 等价于 SQL 中的 and 关键字，比如 findByUsernameAndPassword(String user, Striang pwd)；
      Or --- 等价于 SQL 中的 or 关键字，比如 findByUsernameOrAddress(String user, String addr)；
      Between --- 等价于 SQL 中的 between 关键字，比如 findBySalaryBetween(int max, int min)；
      LessThan --- 等价于 SQL 中的 "<"，比如 findBySalaryLessThan(int max)；
      GreaterThan --- 等价于 SQL 中的">"，比如 findBySalaryGreaterThan(int min)；
      IsNull --- 等价于 SQL 中的 "is null"，比如 findByUsernameIsNull()；
      IsNotNull --- 等价于 SQL 中的 "is not null"，比如 findByUsernameIsNotNull()；
      NotNull --- 与 IsNotNull 等价；
      Like --- 等价于 SQL 中的 "like"，比如 findByUsernameLike(String user)；
      NotLike --- 等价于 SQL 中的 "not like"，比如 findByUsernameNotLike(String user)；
      OrderBy --- 等价于 SQL 中的 "order by"，比如 findByUsernameOrderBySalaryAsc(String user)；
      Not --- 等价于 SQL 中的 "！ ="，比如 findByUsernameNot(String user)；
      In --- 等价于 SQL 中的 "in"，比如 findByUsernameIn(Collection<String> userList) ，方法的参数可以是 Collection 类型，也可以是数组或者不定长参数；
      NotIn --- 等价于 SQL 中的 "not in"，比如 findByUsernameNotIn(Collection<String> userList) ，方法的参数可以是 Collection 类型，也可以是数组或者不定长参数；