/**
 * Copyright (c) 2000-2007 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.portal.upgrade.v4_3_4.util;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.upgrade.util.BaseUpgradeColumnImpl;
import com.liferay.portal.upgrade.util.TempUpgradeColumnImpl;
import com.liferay.util.LocalizationUtil;

/**
 * <a href="JournalArticleContentUpgradeColumnImpl.java.html"><b><i>View Source
 * </i></b></a>
 *
 * @author Alexander Chow
 *
 */
public class JournalArticleContentUpgradeColumnImpl
	extends BaseUpgradeColumnImpl {

	public JournalArticleContentUpgradeColumnImpl(
			TempUpgradeColumnImpl structureIdColumn) {

		super("content");

		_structureIdColumn = structureIdColumn;
	}

	public Object getNewValue(Object oldValue) throws Exception {
		String oldContent = (String)oldValue;

		String structureId = (String)_structureIdColumn.getOldValue();

		if (Validator.isNull(structureId)) {
			if (Validator.isNotNull(oldContent) &&
				(oldContent.indexOf("<static-content") == -1)) {

				String defaultLanguageId = LocaleUtil.toLanguageId(
						LocaleUtil.getDefault());

				oldContent = LocalizationUtil.updateLocalization(
					StringPool.BLANK, "static-content",
					GetterUtil.getString(oldContent), defaultLanguageId,
					defaultLanguageId, true);
			}
		}

		return oldContent;
	}

	private TempUpgradeColumnImpl _structureIdColumn;

}