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

import org.apache.hadoop.util.ToolRunner;
import com.manganit.half.action.HalfJavaAction;
import com.manganit.half.client.HealthCheck;
import com.manganit.half.util.EnvUtils;

/**
 *
 * @author Damien Claveau
 * 
 */
public class Diagnostic extends HalfJavaAction {
   
    public static void main(String[] args) throws Exception {
      int exitCode = ToolRunner.run(new Diagnostic(), args);
    }
    
    @Override
    protected boolean getKerberosDebugEnabled() {
      return false;
    }
    
    @Override
    public void doRun(String[] args) throws Exception {
      printContext();
      boolean healthy = new HealthCheck(this.getConf()).checkAll();
    }
 }
