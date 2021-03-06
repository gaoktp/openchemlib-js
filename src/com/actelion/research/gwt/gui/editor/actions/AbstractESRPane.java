package com.actelion.research.gwt.gui.editor.actions;

import com.actelion.research.gwt.gui.editor.ImageHolder;
import com.actelion.research.gwt.gui.editor.ToolBar;
import com.actelion.research.gwt.gui.viewer.GraphicsContext;
import com.actelion.research.gwt.gui.viewer.Log;
import com.actelion.research.share.gui.editor.Model;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PopupPanel;

/**
 * Project:
 * User: rufenec
 * Date: 4/2/13
 * Time: 2:08 PM
 */

public abstract class AbstractESRPane extends PopupPanel
{

    protected Canvas canvas;
    protected Model model;


    public AbstractESRPane(Model m,final int width, final int height)
    {
        super(true);
        this.model = m;

        canvas = Canvas.createIfSupported();
        canvas.setCoordinateSpaceWidth(width);
        canvas.setCoordinateSpaceHeight(height);
        canvas.setWidth(width + "px");
        canvas.setHeight(height + "px");
//        Log.println("Canvas " + canvas + " " + width);
        setWidget(canvas);

        canvas.addMouseDownHandler(new MouseDownHandler()
        {
            @Override
            public void onMouseDown(MouseDownEvent event)
            {
                int row = (int)(event.getY()/ (height / ToolBar.ESR_IMAGE_ROWS));
//                Log.println("Mouse Down " + row);
                if (row >= 0 && row < 3) {
                    model.setESRType(model.esrTypeFromRow(row));
                }
                onMouseButtonPressed();
                requestLayout();
            }
        });

        canvas.addMouseUpHandler(new MouseUpHandler()
        {
            @Override
            public void onMouseUp(MouseUpEvent event)
            {
                onMouseButtonReleased();
            }
        });

        // This class allows us to modify the pane.
        // This might be necessary for instance with z-index
        // if the editor is placed in an app which modifies the z-index e.g. angular/bootstrap etc.
        getElement().setClassName("ESRPane");
    }

    public abstract void onMouseButtonReleased();
    public abstract void onMouseButtonPressed();

    void requestLayout()
    {
//        Log.println("AbstractESRPane draw()");
        draw(canvas);
    }

    private void draw(Canvas toolBar)
    {
        Context2d context2d = toolBar.getContext2d();
//        context2d.drawImage(ImageElement.as(ESR_BUTTON_UP.getElement()), 0, 0);
        drawButtons(new GraphicsContext(context2d));
    }

    public abstract void drawButtons(GraphicsContext ctx);

}
