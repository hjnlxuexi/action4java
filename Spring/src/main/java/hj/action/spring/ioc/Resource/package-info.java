/**
 * <p>Title : Spring统一资源加载</p>
 * <p>Description :
 * Resource 资源
 *  |
 *  |---AbstractResource 抽象并提供部分实现
 *      |
 *      |--ByteArrayResource
 *      |--ClassPathResource
 *      |--FileSystemResource
 *
 * ResourceLoader：资源加载器
 *  |
 *  |--DefaultResourceLoader ：默认加载器
 *  |   |
 *  |   |--ClassRelativeResourceLoader 类相对路径加载器
 *  |   |--FileSystemResourceLoader  文件系统资源加载器
 *  |
 *  |--ResourcePatternResolver ：模式匹配多个资源
 *      |
 *      |--PathMatchingResourcePatternResolver ：内部代理ResourceLoader
 *
 * </p>
 * <p>Date : 2019-01-21 </p>
 *
 * @author : hejie
 */
package hj.action.spring.ioc.Resource;