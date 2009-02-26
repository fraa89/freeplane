/*
 *  Freeplane - mind map editor
 *  Copyright (C) 2008 Joerg Mueller, Daniel Polansky, Christian Foltin, Dimitry Polivaev
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.freeplane.features.mindmapmode.addins.export;

import java.io.File;

import javax.swing.JFileChooser;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.freeplane.core.util.LogTool;

/**
 * @author joerg
 */
class XmlExporter {
	final JFileChooser fc = new JFileChooser();

	/** Creates a new instance of XmlExporter */
	public XmlExporter() {
	}
	/**
	 * The method actually performing the XSLT transformation.
	 * @param xmlFile the source XML file
	 * @param xsltFile the XSLT sheet describing the transformation
	 * @param resultFile the target file (can be XML or something else)
	 */
	public void transForm(final File xmlFile, final File xsltFile, final File resultFile) {
		final Source xmlSource = new StreamSource(xmlFile);
		final Source xsltSource = new StreamSource(xsltFile);
		final Result result = new StreamResult(resultFile);
		try {
			final TransformerFactory transFact = TransformerFactory.newInstance();
			final Transformer trans = transFact.newTransformer(xsltSource);
			trans.transform(xmlSource, result);
		}
		catch (final Exception e) {
			LogTool.logException(e);
		};
		return;
	}
}
