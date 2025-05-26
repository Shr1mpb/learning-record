package homework.oop.h14;

import java.io.*;
import java.util.*;

/**
 * 在本包下增加合适的类和方法， 本程序不但要测试通过，还需要写适当的注释
 * 
 * 不要引用jdk1.8以外第三方的包
 * 
 * @author cjy
 *
 */
public class MyTools {
	private String studentListFile;
	private String picDir;
	/**
	 * @param studentListFile 存放学生名单的文件名
	 * @param picDir          图片存放的目录（不会包含子目录）
	 */
	public MyTools(String studentListFile, String picDir) {
		this.studentListFile = studentListFile;
		this.picDir = picDir;
		// TODO Auto-generated constructor stub
	}

	public MyTools() {

	}

	private static class Student{
		private String name;
		private String sno;
		private String classId;

		private Student(String name, String sno, String classId) {
			this.name = name;
			this.sno = sno;
			this.classId = classId;
		}

		private Student(String sno) {
			this.sno = sno;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Student student = (Student) o;
			return Objects.equals(sno, student.sno);
		}

		@Override
		public int hashCode() {
			return Objects.hashCode(sno);
		}
	}

	public Set<String> copyToTargetDirAndReturnNoExist(String studentListFile,String srcDir,String target) throws Exception {
		String s = null;
		List<Student> list = new ArrayList<>();
		Set<String> snoList = new HashSet<>();
		try(
				BufferedReader br = new BufferedReader(new FileReader(studentListFile));
		){
			// 逐行读取并记录每一个学生的信息到list中
            while ((s = br.readLine()) != null) {
				String[] splitedString = s.split("\t");
				list.add(new Student(splitedString[1], splitedString[0], splitedString[2]));
				snoList.add(splitedString[0]);// 这个是要存储未上传的同学的信息
			}
			// 找到没有交文件的学生
			File dir = new File(srcDir);
			File[] files = dir.listFiles();
			for (File file : files) {
				if (file.isFile()) {
					String name = file.getName();
					String[] split = name.split("\\.");
					snoList.remove((String)split[0]);// 上传过的就从全部名单里删除
				}
			}
			// 先从总体中删除未上传的人 (注意这里重写了一下Student类的equals和hashCode方法)
			List<Student> notUploadList = new ArrayList<>();
			for (String noUploadSno : snoList) {
				notUploadList.add(new Student(noUploadSno));
			}
			list.removeAll(notUploadList);
			// 把已上传的人的文件整理到对应的目录中去
			tidyAlreadyUpload(list, srcDir, target);

		}

		// 返回未上传的信息
		return snoList;
	}

	/**
	 * 将指定学生的文件都整理到指定的目录
	 * 目录为target文件夹下的 target/班级id/学号_姓名.jpg
	 * @param alreadUploadlist 已上传文件的学生集合
	 * @param src 源上传文件目录
	 * @param target 目标目录
	 */
	private void tidyAlreadyUpload(List<Student> alreadUploadlist,String src, String target) throws IOException {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		File srcDir = new File(src);
		File targetDir = new File(target);
		try {
			for (Student student : alreadUploadlist) {
				// 开始为每一个上传过文件的同学整理文件(拷贝)
				// 先获取该同学的班级并在targetDir下创建目录
				String classId = student.classId;
				File newTargetDir = new File(targetDir, classId);
				boolean mkdirs = newTargetDir.mkdirs();
				if (!mkdirs) { // 创建失败输出一下异常
					System.out.println("目录" + newTargetDir.getAbsolutePath() + "已存在或创建失败！");
				}
				// 创建好目录后 获取源文件
				File srcFile = new File(srcDir, student.sno + ".jpg");
				fis = new FileInputStream(srcFile);
				// 输出到目标目录
				File newFile = new File(newTargetDir, student.sno + "_" + student.name + ".jpg");
				fos = new FileOutputStream(newFile);
				// 读取每一个字节并写入到输出流
				int byteContent;
				byte[] buffer = new byte[1024];
				while ((byteContent = fis.read(buffer)) != -1) {
					fos.write(byteContent);
				}

			}
		}catch (Exception e){
			System.out.println(e.getMessage());
		}finally {
			if (fis != null) {
				fis.close();
			}
			if (fos != null) {
				fos.close();
			}
		}
	}


}
