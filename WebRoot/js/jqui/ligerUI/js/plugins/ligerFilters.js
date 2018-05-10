/**
* jQuery ligerUI 1.2.2
* 
* http://ligerui.com
*  
* Author daomi 2013 [ gd_star@163.com ] 
* 
*/
(function ($)
{
	var k = 0 ;
    $.fn.ligerFilter = function ()
    {
        return $.ligerui.run.call(this, "ligerFilter", arguments);
    };

    $.fn.ligerGetFilterManager = function ()
    {
        return $.ligerui.run.call(this, "ligerGetFilterManager", arguments);
    };
    $.ligerDefaults.Filter = {
        //字段列表
        fields: [],
        //字段类型 - 运算符 的对应关系
        operators: {},
        //自定义输入框(如下拉框、日期)
        editors: {},
    };
    $.ligerDefaults.FilterString = {
        strings: {
            "and": "并且",
            "or": "或者",
            "equal": "相等",
            "notequal": "不相等",
            "startwith": "以..开始",
            "endwith": "以..结束",
            "like": "相似",
            "greater": "大于",
            "greaterorequal": "大于或等于",
            "less": "小于",
            "lessorequal": "小于或等于",
            "in": "包括在...",
            "notin": "不包括...",
            "addgroup": "增加分组",
            "addrule": "增加条件",
            "deletegroup": "删除分组"
        }
    };

    $.ligerDefaults.Filter.operators['string'] =
    $.ligerDefaults.Filter.operators['text'] =
    ["equal", "notequal", "startwith", "endwith", "like", "greater", "greaterorequal", "less", "lessorequal", "in", "notin"];

    $.ligerDefaults.Filter.operators['number'] =
    $.ligerDefaults.Filter.operators['int'] =
    $.ligerDefaults.Filter.operators['float'] =
    $.ligerDefaults.Filter.operators['date'] =
    ["equal", "notequal", "greater", "greaterorequal", "less", "lessorequal", "in", "notin"];

    $.ligerDefaults.Filter.editors['string'] =
    {
        create: function (container, field)
        {
            var input = $("<input type='text'/>");
            container.append(input);
            input.ligerTextBox(field.editor.options || {});
            return input;
        },
        setValue: function (input, value)
        {
            input.val(value);
        },
        getValue: function (input)
        {
            return input.liger('option', 'value');
        },
        destroy: function (input)
        {
            input.liger('destroy');
        }
    };

    $.ligerDefaults.Filter.editors['date'] =
    {
        create: function (container, field)
        {
            var input = $("<input type='text'/>");
            container.append(input);
            input.ligerDateEditor(field.editor.options || {});
            return input;
        },
        setValue: function (input, value)
        {
            input.liger('option', 'value', value);
        },
        getValue: function (input, field)
        {
            return input.liger('option', 'value');
        },
        destroy: function (input)
        {
            input.liger('destroy');
        }
    };

    $.ligerDefaults.Filter.editors['number'] =
    {
        create: function (container, field)
        {
            var input = $("<input type='text'/>");
            container.append(input);
            var options = {
                minValue: field.editor.minValue,
                maxValue: field.editor.maxValue
            };
            input.ligerSpinner($.extend(options, field.editor.options || {}));
            return input;
        },
        setValue: function (input, value)
        {
            input.val(value);
        },
        getValue: function (input, field)
        {
            var isInt = field.editor.type == "int";
            if (isInt)
                return parseInt(input.val(), 10);
            else
                return parseFloat(input.val());
        },
        destroy: function (input)
        {
            input.liger('destroy');
        }
    };

    $.ligerDefaults.Filter.editors['combobox'] =
    {
        create: function (container, field)
        {
            var input = $("<input type='text'/>");
            container.append(input);
            var options = {
                data: field.data,
                slide: false,
                valueField: field.editor.valueField || field.editor.valueColumnName,
                textField: field.editor.textField || field.editor.displayColumnName
            };
            $.extend(options, field.editor.options || {});
            input.ligerComboBox(options);
            return input;
        },
        setValue: function (input, value)
        {
            input.liger('option', 'value', value);
        },
        getValue: function (input)
        {
            return input.liger('option', 'value');
        },
        destroy: function (input)
        {
            input.liger('destroy');
        }
    };

    //过滤器组件
    $.ligerui.controls.Filter = function (element, options)
    {
        $.ligerui.controls.Filter.base.constructor.call(this, element, options);
    };
	var s = 1;
    $.ligerui.controls.Filter.ligerExtend($.ligerui.core.UIComponent, {
        __getType: function ()
        {
            return 'Filter'
        },
        __idPrev: function ()
        {
            return 'Filter';
        },
        _init: function ()
        {
            $.ligerui.controls.Filter.base._init.call(this);
        },
        _render: function ()
        {
            var g = this, p = this.options;

            g.set(p);

            //事件：增加分组
            $("#" + g.id + " .addgroup").live('click', function ()
            {
                var jtable = $(this).parent().parent().parent().parent();
                g.addGroup(jtable);
            });
            //事件：删除分组
            $("#" + g.id + " .deletegroup").live('click', function ()
            {
                var jtable = $(this).parent().parent().parent().parent();
                g.deleteGroup(jtable);
            });
            //事件：增加条件
            $("#" + g.id + " .addrule").live('click', function ()
            {
                var jtable = $(this).parent().parent().parent().parent();
                g.addRule(jtable);
            });
            //事件：删除条件
            $("#" + g.id + " .deleterole").live('click', function ()
            {
                var rulerow = $(this).parent().parent();
                g.deleteRule(rulerow);
            });

        },

        //设置字段列表
        _setFields: function (fields)
        {
            var g = this, p = this.options;
            if (g.group) g.group.remove();
            g.group = $(g._bulidGroupTableHtml()).appendTo(g.element);
        },

        //输入框列表
        editors: {},

        //输入框计算器
        editorCounter: 0,

        //增加分组
        //parm [jgroup] jQuery对象(主分组的table dom元素)
        addGroup: function (jgroup)
        {
            var g = this, p = this.options;
            jgroup = $(jgroup || g.group);
            var lastrow = $(">tbody:first > tr:last", jgroup);
            var groupHtmlArr = [];
            groupHtmlArr.push('<tr class="l-filter-rowgroup"><td class="l-filter-cellgroup" colSpan="4">');
            var altering = !jgroup.hasClass("l-filter-group-alt");
            groupHtmlArr.push(g._bulidGroupTableHtml(altering, true));
            groupHtmlArr.push('</td></tr>');
            var row = $(groupHtmlArr.join(''));
            lastrow.before(row);
            return row.find("table:first");
        },

        //删除分组 
        deleteGroup: function (group)
        {
            var g = this, p = this.options;
            $("td.l-filter-value", group).each(function ()
            {
                var rulerow = $(this).parent();
                $("select.fieldsel", rulerow).unbind();
                g.removeEditor(rulerow);
            });
            $(group).parent().parent().remove();
        },


        //删除编辑器
        removeEditor: function (rulerow)
        {
            var g = this, p = this.options;
            var type = $(rulerow).attr("editortype");
            var id = $(rulerow).attr("editorid");
            var editor = g.editors[id];
            if (editor) p.editors[type].destroy(editor);
            $("td.l-filter-value:first", rulerow).html("");
        },

        //设置规则
        //parm [group] 分组数据
        //parm [jgruop] 分组table dom jQuery对象
        setData: function (group, jgroup)
        {
            var g = this, p = this.options;
            jgroup = jgroup || g.group;
            var lastrow = $(">tbody:first > tr:last", jgroup);
            jgroup.find(">tbody:first > tr").not(lastrow).remove();
            $("select:first", lastrow).val(group.op);
            if (group.rules)
            {
                $(group.rules).each(function ()
                {
                    var rulerow = g.addRule(jgroup);
                    rulerow.attr("fieldtype", this.type || "string");
                    $("select.opsel", rulerow).val(this.op);
                    var editorid = rulerow.attr("editorid");
                    if (editorid && g.editors[editorid])
                    {
                        var field = g.getField(this.field);
                        if (field && field.editor)
                        {
                            p.editors[field.editor.type].setValue(g.editors[editorid], this.value, field);
                        }
                    }
                    else
                    {
                        $(":text", rulerow).val(this.value);
                    }
                });
            }
            if (group.groups)
            {
                $(group.groups).each(function ()
                {
                    var subjgroup = g.addGroup(jgroup);
                    g.setData(this, subjgroup);
                });
            }
        },

        //增加一个条件
        //parm [jgroup] 分组的jQuery对象
        addRule: function (jgroup)
        {
            var g = this, p = this.options;
            
            jgroup = jgroup || g.group;
            var lastrow = $(">tbody:first > tr:last", jgroup);
            var rulerow = $(g._bulidRuleRowHtml());
            lastrow.before(rulerow);
            if (p.fields.length)
            {
                //如果第一个字段启用了自定义输入框
                g.appendEditor(rulerow, p.fields[0]);
            }
            var str = [];
            g.result(k);
            var bbb="#tractId"+k;
            $(bbb).ligerComboBox({ data: str, isMultiSelect: true });	
            var data = p.fields.conditions;//通道信息
            for (var i = 0, l = data.length; i < l; i++)
	        {
            	str += "{id:'"+data[i].display+"',text:'"+data[i].name+"("+data[i].value+")'},";
	        }
            if(str != '['){
            	str = '['+str.substring(0,str.length-1);
            }
            str+="]";
            g.result(k);
           str =eval('(' + str + ')');
           var docu="#tractId"+k;
            var select = $(docu).ligerComboBox({ data: str, isMultiSelect: true });
            select._setWidth(200);
          //事件：字段列表改变时
            /*$("select.mcc"+k+",select.fieldsel"+k, rulerow).bind('change', function ()
            {	
            	var str4 = ($(this).val()).split(",");
            	var bj = str4[1];
            	var str2 = ($(".fieldsel"+bj).val()).split(",");
            	var str3 = ($(".mcc"+bj).val()).split(",");
                var fieldName = str2[0];
                var data = p.fields.conditions2;//通道信息
                var mcc = str3[0];
                var field = g.getMcc(mcc);
                if (mcc != -1 || fieldName != -1)
                {
                	var cell = $(".l-filter-value select", rulerow);
	                var cls = cell.attr("class");
	                $('.'+cls).empty();
	                var str = '[';
	                for (var i = 0, l = data.length; i < l; i++)
			        {
	                	var a = (data[i].value).split(",")[0];
	                	var b = (data[i].value).split(",")[1];
	                	if(mcc != -1 && fieldName != -1){
		                	if(fieldName == a && mcc == b) {
	                			var field = data[i];
			                	str += "{id:'"+field.display+"',text:'"+field.name+"'},";
		                	}
	                	}else if(fieldName == a){
	                		var field = data[i];
		                	str += "{id:'"+field.display+"',text:'"+field.name+"'},";
	                	}else if(mcc == b){
	                		var field = data[i];
		                	str += "{id:'"+field.display+"',text:'"+field.name+"'},";
	                	}
			        }
	                if(str != '[')
	                	str = str.substring(0,str.length-1);
	                str+="]";
	                g.result(bj);
	               str =eval('(' + str + ')');
	               var docu="#tractId"+bj;
	                $(docu).ligerComboBox({ data: str, isMultiSelect: true });
                } else
                {
                	var str=[];
            		g.result(bj);
            		$("#tractId"+bj).ligerComboBox({ data: str, isMultiSelect: true });
                }
            });*/
            k++;
            return rulerow;
        },
        result:function(num){
        	var str1 = "<div class='l-text-wrapper' style='float:left;width:210px'><div class='l-text l-text-combobox l-text-focus' style='width:200px'><input type='text' id='tractId"+num+"' class='l-text-field valtxt' ligerui-id='tractId"+num+"'/></div><input type='hidden' name='tractId"+num+"_val' id='tractId"+num+"_val' data-ligerid='tractId"+num+"'/></div>";
    		$(".l-filter-value"+num).html("");
    		$(".l-filter-value"+num).html(str1);
        },
        //删除一个条件
        deleteRule: function (rulerow)
        {
            $("select.fieldsel", rulerow).unbind();
            this.removeEditor(rulerow);
            $(rulerow).remove();
        },

        //附加一个输入框
        appendEditor: function (rulerow, field)
        {
            var g = this, p = this.options;
            if (g.enabledEditor(field))
            {
                var cell = $("td.l-filter-value:first", rulerow).html("");
                var editor = p.editors[field.editor.type];
                g.editors[++g.editorCounter] = editor.create(cell, field);
                rulerow.attr("editortype", field.editor.type).attr("editorid", g.editorCounter);
            }
        },

        //获取分组数据
        getData: function (group)
        {
            var g = this, p = this.options;
            group = group || g.group;

            var groupData = {};
            var f = true;
            $("> tbody > tr", group).each(function (i, row)
            {
            	if(f){
	                var rowlast = $(row).hasClass("l-filter-rowlast");
	                var rowgroup = $(row).hasClass("l-filter-rowgroup");
	                if (rowgroup)
	                {
	                    var groupTable = $("> td:first > table:first", row);
	                    if (groupTable.length)
	                    {
	                        if (!groupData.groups) groupData.groups = [];
	                        groupData.groups.push(g.getData(groupTable));
	                    }
	                }
	                else if (rowlast)
	                {
	                    groupData.op = $(".groupopsel:first", row).val();
	                }
	                else
	                {
	                    var beginAmt = $(".beginAmt"+i, row).val();
	                    if(beginAmt==null || beginAmt.length == 0 ){
	                    	alert("请输入起始金额");
	                    	f = false;
	                    	return null;
	                    }
	                    var endAmt = $(".endAmt"+i, row).val();
	                    if(endAmt==null || endAmt.length == 0 ){
	                    	alert("请输入结束金额");
	                    	f = false;
	                    	return null;
	                    }
	                    var tractId = $("#tractId"+i+"_val", row).val();
	                    if(tractId==null || tractId.length == 0 ){
	                    	alert("请选择通道");
	                    	f = false;
	                    	return null;
	                    }
	                    var type = $(row).attr("fieldtype") || "string";
	                    if (!groupData.rules) groupData.rules = [];
	                    groupData.rules.push({
	                    	beginAmt: beginAmt,endAmt:endAmt,tractId:tractId
	                    });
	                }
            	}
            });

            return groupData;
        },

        _getRuleValue: function (rulerow, field)
        {
            var g = this, p = this.options;
            var editorid = $(rulerow).attr("editorid");
            var editortype = $(rulerow).attr("editortype");
            var editor = g.editors[editorid];
            if (editor)
                return p.editors[editortype].getValue(editor, field);
            return $("#tractId"+s, rulerow).val();
        },

        //判断某字段是否启用自定义的输入框  
        enabledEditor: function (field)
        {
            var g = this, p = this.options;
            if (!field.editor || !field.editor.type) return false;
            return (field.editor.type in p.editors);
        },

        //根据fieldName 获取 字段
        getField: function (fieldname)
        {
            var g = this, p = this.options;
            for (var i = 0, l = p.fields.conditions.length; i < l; i++)
            {	
                var field = p.fields.conditions[i];
                if (field.name == fieldname) return field;
            }
            return null;
        },
      //根据mcc 获取 字段
        getMcc: function (fieldname)
        {
            var g = this, p = this.options;
            for (var i = 0, l = p.fields.conditions3.length; i < l; i++)
            {	
                var field = p.fields.conditions3[i];
                if (field.display == fieldname) return field;
            }
            return null;
        },
        //获取一个分组的html
        _bulidGroupTableHtml: function (altering, allowDelete)
        {
            var g = this, p = this.options;
            var tableHtmlArr = [];
            tableHtmlArr.push('<table cellpadding="0" cellspacing="0" border="0" class="l-filter-group');
            if (altering)
                tableHtmlArr.push(' l-filter-group-alt');
            tableHtmlArr.push('"><tbody>');
            tableHtmlArr.push('<tr class="l-filter-rowlast"><td class="l-filter-rowlastcell" align="right" colSpan="4">');
            /**注释掉and 和 or 的下拉框
            //and or
            tableHtmlArr.push('<select class="groupopsel">');
            tableHtmlArr.push('<option value="and">' + p.strings['and'] + '</option>');
            tableHtmlArr.push('<option value="or">' + p.strings['or'] + '</option>');
            tableHtmlArr.push('</select>');

            //add group
            tableHtmlArr.push('<input type="button" value="' + p.strings['addgroup'] + '" class="addgroup">');
            */
            //add rule
            tableHtmlArr.push('<font style="color:red">说明：请至少选择2、3中的1种条件，再请选择通道类型</font>');
            tableHtmlArr.push('<input type="button" value="' + p.strings['addrule'] + '" class="addrule">');
            if (allowDelete)
                tableHtmlArr.push('<input type="button" value="' + p.strings['deletegroup'] + '" class="deletegroup">');

            tableHtmlArr.push('</td></tr>');

            tableHtmlArr.push('</tbody></table>');
            return tableHtmlArr.join('');
        },
		
        //获取字段值规则的html
        _bulidRuleRowHtml: function (fields)
        {
        	data = fields;
            var g = this, p = this.options;
            fields = fields || p.fields;
            var rowHtmlArr = [];
            var fieldType = fields.conditions[0].type || "string";
            rowHtmlArr.push('<tr fieldtype="' + fieldType + '">');
            rowHtmlArr.push('<td class="l-filter-cardType">');
            rowHtmlArr.push('<font style="color:red">起始金额&nbsp;</font>');
            rowHtmlArr.push('<input class="beginAmt'+k+'">');
            
            rowHtmlArr.push('<td class="l-filter-cardType">');
            rowHtmlArr.push('<font style="color:red">结束金额&nbsp;</font>');
            rowHtmlArr.push('<input class="endAmt'+k+'">');
            rowHtmlArr.push('</td>');


            rowHtmlArr.push('<td class="l-filter-value">');
           // rowHtmlArr.push('<font style="color:red">通道<select class="valtxt'+s+'" id="tractId">');
            rowHtmlArr.push('<font style="color:red">通道&nbsp;</font></td><td class="l-filter-value'+k+'">');
            
            //rowHtmlArr.push('<option value="-1,-1"');
            //if (i == 0) rowHtmlArr.push(" selected ");
            //rowHtmlArr.push('>');
           // rowHtmlArr.push("--请选择--");
            //rowHtmlArr.push('</option>');
           // rowHtmlArr.push("</select></font>");
            rowHtmlArr.push('</td>');
           
            rowHtmlArr.push('<td>');
            rowHtmlArr.push('<div class="l-icon-cross deleterole"></div>');
            rowHtmlArr.push('</td>');
            rowHtmlArr.push('</tr>');
            return rowHtmlArr.join('');
        },

        //获取一个运算符选择框的html
        _bulidOpSelectOptionsHtml: function (fieldType)
        {
            var g = this, p = this.options;
            var ops = p.operators[fieldType];
            var opHtmlArr = [];
            for (var i = 0, l = ops.length; i < l; i++)
            {	
                var op = ops[i];
                opHtmlArr[opHtmlArr.length] = '<option value="' + op + '">';
                opHtmlArr[opHtmlArr.length] = p.strings[op];
                opHtmlArr[opHtmlArr.length] = '</option>';
            }
            return opHtmlArr.join('');
        }


    });

})(jQuery);