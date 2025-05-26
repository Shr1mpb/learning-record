package homework.frontend.h58;

public class Test {

	public static void main(String[] args) {

		// TODO Auto-generated method stub
		try {
			// 本题目复杂度比较高，请大家耐心逐步完成,实际上是实现了一个简单的FTP的服务器端和客户端(去掉了用户验证的步骤)
			// 口令文件参见 my_user.txt
			// 测试目录参见 /ftp_home/
			String ip="127.0.0.1";
			int port=12345;
			//下面两个地方要根据本机情况修改
			String remoteHome="D:\\JavaProjects\\Common\\src\\main\\java\\homework\\frontend\\h58\\ftp_home\\";
			//1 构造远程的FTPServer 分别是监听的主机IP 端口 远程FTP根
			MyFTPServer server=new MyFTPServer(ip,port,remoteHome );
			//2 启动远程server
			server.start();
			System.out.println("第1步 启动远程server 完成 !");

			//构造远程FTPFile,这个文件指向的是远程的根目录
			FTPFile ftpFile=new FTPFile(server,"/");
			//按名字升序列出子目录和文件 ，listFiles() 按名字升序列出孩子(先列目录，然后列文件，按升序)，放到一个数组中
			FTPFile[] children=ftpFile.listFiles();
			FTPFile c0=children[0];
			FTPFile c1=children[1];
			FTPFile c2=children[2];
			System.out.println("第2步 如果下面3个打印出来都是true 则 listFiles() 方法 完成 !");
			// isDirectory() 是否是目录； isFile()是否是文件; getPathFileName() 得到从remoteHome 相对算其的路径+文件名 以/开头，路径分隔符用 /
			System.out.println(c0.isDirectory()&&(!c0.isFile())&&"/dir1/".equals(c0.getPathFileName()));
			System.out.println(!c1.isDirectory()&&(c1.isFile())&&"/a.txt".equals(c1.getPathFileName()));
			System.out.println(!c2.isDirectory()&&(c2.isFile())&&"/b.txt".equals(c2.getPathFileName()));

			//尝试读取远程内容
			byte[] data=c2.readToBytes();
			if("helllowb".equals(new String(data))) {
				System.out.println("第3步 读取远程文件内容通过!");
			}

			if(c2.length()==8) {
				System.out.println("第4步 检索远程文件信息通过!");
			}

			FTPFile ftpFile4=new FTPFile(server,"/dir1/c.txt");
			if(ftpFile4.length()==3&&"123".equals(new String(ftpFile4.readToBytes()))) {
				System.out.println("第5步 检索远程文件信息通过!");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
