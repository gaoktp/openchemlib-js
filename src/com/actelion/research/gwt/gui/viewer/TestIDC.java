/*

Copyright (c) 2015-2017, cheminfo

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted provided that the following conditions are met:

    * Redistributions of source code must retain the above copyright notice,
      this list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above copyright notice,
      this list of conditions and the following disclaimer in the documentation
      and/or other materials provided with the distribution.
    * Neither the name of {{ project }} nor the names of its contributors
      may be used to endorse or promote products derived from this software
      without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

*/

package com.actelion.research.gwt.gui.viewer;

import com.actelion.research.chem.Canonizer;
import com.actelion.research.chem.IDCodeParser;
import com.actelion.research.chem.StereoMolecule;

/**
 * Created by rufenec on 20/03/15.
 */
public class TestIDC
{
    public static void main(String[] args)
    {
        String idCode = "gFp@DiTvjh@ !Bg~w@k_}mvw@`";

//        Log.java:70 gFp@DiTvjh@ !B_g~w_K_}mvw_@

        try {
            StereoMolecule mol = new StereoMolecule();
            IDCodeParser p = new IDCodeParser(false);
            System.out.println("parsing idcode");
            if (idCode != null) {
                String[] parts = idCode.split(" ");
                if (parts.length > 1) {
                    p.parse(mol, parts[0], parts[1]);
                } else
                    p.parse(mol, idCode);


                System.out.println("renderMolecule: " + mol.isFragment());
                System.out.println(idCode);
                Canonizer can = new Canonizer(mol);
                String n = can.getIDCode();
                String c = can.getEncodedCoordinates();
                System.out.println("afterrender " + mol.isFragment());
                System.out.println(n + " " + c);

                p.parse(mol, n, c);

            }
        } catch (Exception a) {

        }
    }
}
