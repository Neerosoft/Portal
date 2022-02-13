package portal.org.util;
import org.apache.commons.codec.digest.DigestUtils;
public class MD5 {
	
	public MD5() {
		
	}
	public String EncriptarMD5(String texto) {
		String output="";
		try {
			output=DigestUtils.md5Hex(texto);
			System.out.println("\nEncriptando "+texto+" ---->  "+output+"\n");
			
		}
		catch(Exception e) {
			System.out.println("\nError al encriptar la cadena "+e+"\n");
			e.printStackTrace();
		}
		
		return output;
		
	}

}
