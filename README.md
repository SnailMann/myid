## myid
Global unique ID service based on mysql (number segment)
- [如何实现一个高性能的全局唯一 ID 生成服务](https://blog.csdn.net/SnailMann/article/details/113357180)

### modules
|modules| description|remark|
| --- | --- | --- |
|myid-client| client ||
|myid-server| server ||


### how to start ? 
- deploying myid-server
- user myid-client or request HTTP interface

### preference
- the throughput of single machine (4C8G spot) is 10 k/s


                  
                  
### reference

- [Leaf - @meituan](https://tech.meituan.com/2019/03/07/open-source-project-leaf.html)
- [didi/tinyid - @didi](https://github.com/didi/tinyid)    

  
