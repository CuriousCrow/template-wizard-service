package org.curiouscrow.wizardservice.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Utility class with ZIP-archive building static methods
 */
public class ZipDirectory {
    public static void main(String[] args) throws IOException {
        Path sourcePath = Paths.get("out", "sample_maven");
        String destinationFolder = "results";
        zipFolder(sourcePath, destinationFolder);
    }

    /** Zip folder
     * @param sourcePath folder to be zipped
     * @param destinationPath result output directory
     * @param resultFilename resulting zip filename
     * @throws IOException common exception
     * */
    public static void zipFolder(Path sourcePath, String destinationPath, String resultFilename) throws IOException {
        if (resultFilename.isEmpty()) {
            resultFilename = sourcePath.getFileName().toString();
        }

        FileOutputStream fos = new FileOutputStream(destinationPath + "/" + resultFilename + ".zip");
        ZipOutputStream zipOut = new ZipOutputStream(fos);

        File fileToZip = new File(sourcePath.toString());
        zipFile(fileToZip, fileToZip.getName(), zipOut);
        zipOut.close();
        fos.close();
    }

    /** Zip folder with default zip filename
     * @param destinationPath path to save resulting zip
     * @param sourcePath folder to be zipped
     * @throws IOException common exception
     * */
    public static void zipFolder(Path sourcePath, String destinationPath) throws IOException {
        zipFolder(sourcePath, destinationPath, "");
    }

    /** Inner Zip file/folder building method
     * @param fileName resulting zip file name
     * @param fileToZip file to be zipped
     * @param zipOut output zip stream
     * */
    private static void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
        if (fileToZip.isHidden()) {
            return;
        }
        if (fileToZip.isDirectory()) {
            String name = fileName + (fileName.endsWith("/") ? "" : "/");
            zipOut.putNextEntry(new ZipEntry(name));
            zipOut.closeEntry();

            File[] children = fileToZip.listFiles();
            if (children == null) {
                return;
            }
            for (File childFile : children) {
                zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
            }
            return;
        }
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileName);
        zipOut.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }
        fis.close();
    }
}