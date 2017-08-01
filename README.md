# yuyufeng
     cascade表示级联操作  
     CascadeType.MERGE级联更新：若items属性修改了那么order对象保存时同时修改items里的对象。对应EntityManager的merge方法   
     CascadeType.REFRESH级联刷新：获取order对象里也同时也重新获取最新的items时的对象。对应EntityManager的refresh(object)方法有效。即会重新查询数据库里的最新数据  
     CascadeType.PERSIST级联保存：对order对象保存时也对items里的对象也会保存。对应EntityManager的presist方法   
     CascadeType.REMOVE级联删除：对order对象删除也对items里的对象也会删除。对应EntityManager的remove方法 
## 