/*
 * Copyright 2017 ManganIT.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.manganit.half.quickstart;

import com.manganit.half.action.HalfJavaAction;
import java.io.IOException;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.util.ToolRunner;

/**
 *
 * @author Damien Claveau
 *
 */
public class DirectoryLister extends HalfJavaAction  {
    
    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new DirectoryLister(), args);
    }

    @Override
    public void doRun(String[] args) throws Exception {
        if (args.length != 1) {
          System.out.println("Usage : DirectoryLister [path]");
        }
        else {
          printDirectoryContent(getFileSystem(), args[0]);
        }
    }
    
    private static void printDirectoryContent(FileSystem fs, String directory) throws IOException {
        Path path = new Path(directory);
        if (fs.exists(path)) {
            FileStatus[] files = fs.listStatus(path);
            for (FileStatus file : files) {
                System.out.println(file.getOwner() + ":" + file.getGroup() + "\t" + file.getPath().getName());
            }
        }
    }
}
